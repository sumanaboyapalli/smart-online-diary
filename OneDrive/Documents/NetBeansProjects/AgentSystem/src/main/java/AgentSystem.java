/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sumana
 */
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AgentSystem {
      private final int DISCOVERY_PORT = 8888;
    private final int TIMEOUT = 5000; // in milliseconds
    private final Map<String, List<Agent>> localAgents = new ConcurrentHashMap<>();

    public void registerAgent(Agent agent, String className) {
        localAgents.computeIfAbsent(className, k -> new ArrayList<>()).add(agent);
    }

    public List<Agent> lookup(String className) {
        return localAgents.getOrDefault(className, Collections.emptyList());
    }

    public void sendMessage(String message, String fromAgentId, String toAgentId) {
        for (List<Agent> agents : localAgents.values()) {
            for (Agent agent : agents) {
                if (agent.getAgentId().equals(toAgentId)) {
                    agent.receive(message, fromAgentId);
                }
            }
        }
    }

    public void startAgent(String className) {
        String agentId = UUID.randomUUID().toString();
        Agent agent = null;
        if ("PingAgent".equals(className)) {
            agent = new PingAgent(agentId, this);
        } else if ("PongAgent".equals(className)) {
            agent = new PongAgent(agentId, this);
        }
        if (agent != null) {
            registerAgent(agent, className);
            if (agent instanceof PingAgent) {
                ((PingAgent) agent).start();
            }
        }
    }

   public void discoverRemoteAgents() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            executorService.submit(this::sendDiscoveryRequest);
            executorService.submit(this::receiveDiscoveryResponse);
        } finally {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendDiscoveryRequest() {
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setBroadcast(true);

            String broadcastMessage = "DISCOVERY_REQUEST";
            byte[] buffer = broadcastMessage.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
                    InetAddress.getByName("255.255.255.255"), DISCOVERY_PORT);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveDiscoveryResponse() {
        try (DatagramSocket socket = new DatagramSocket(DISCOVERY_PORT)) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Set the timeout for receiving packets.
            socket.setSoTimeout(TIMEOUT);

            while (true) {
                socket.receive(packet);
                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                if ("DISCOVERY_REQUEST".equals(receivedMessage)) {
                    System.out.println("Discovery request received from: " + packet.getAddress());

                    // Respond to Discovery Request
                    String responseMessage = "DISCOVERY_RESPONSE";
                    byte[] responseBuffer = responseMessage.getBytes();
                    DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length,
                            packet.getAddress(), packet.getPort());
                    socket.send(responsePacket);
                }
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Discovery time out.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

