/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sumana
 */
public class Main {
    public static void main(String[] args) {
        AgentSystem agentSystem = new AgentSystem();
        agentSystem.startAgent("PongAgent");
        agentSystem.startAgent("PingAgent");
         agentSystem.discoverRemoteAgents();
    }
}

