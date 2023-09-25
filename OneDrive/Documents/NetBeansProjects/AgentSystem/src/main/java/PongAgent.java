/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sumana
 */
public class PongAgent extends Agent {
    public PongAgent(String agentId, AgentSystem agentSystem) {
        super(agentId, agentSystem);
    }

    @Override
    public void receive(String message, String fromAgentId) {
        System.out.println("PongAgent[" + agentId + "]: Received " + message + " from " + fromAgentId);
        if ("ping".equals(message)) {
            agentSystem.sendMessage("pong", agentId, fromAgentId);
        }
    }
}
