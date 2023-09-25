/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sumana
 */
public abstract class Agent {
    protected String agentId;
    protected AgentSystem agentSystem;

    public Agent(String agentId, AgentSystem agentSystem) {
        this.agentId = agentId;
        this.agentSystem = agentSystem;
    }

    public abstract void receive(String message, String fromAgentId);

    public String getAgentId() {
        return agentId;
    }
}
