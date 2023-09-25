/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sumana
 */
public class PingAgent extends Agent {
    public PingAgent(String agentId, AgentSystem agentSystem) {
        super(agentId, agentSystem);
    }

    public void start() {
        for (Agent agent : agentSystem.lookup("PongAgent")) {
            sendMessage("ping", agent.getAgentId());
        }
    }

    @Override
    public void receive(String message, String fromAgentId) {
        System.out.println("PingAgent[" + agentId + "]: Received " + message + " from " + fromAgentId);
    }

    private void sendMessage(String message, String toAgentId) {
        agentSystem.sendMessage(message, agentId, toAgentId);
    }
}

