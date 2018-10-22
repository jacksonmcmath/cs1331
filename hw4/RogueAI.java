/**
* RogueAI class, extending AI
*
* This class can interact with the firewall in the cannon's software and set
* alert levels. RogueAI can decrease the firewall at the expense of raising the
* alert level. If the alert level exceeds the maximum alert level, then the self
* destruct virus is triggered, and that instance of RogueAI is destroyed.
*
* @author jmcmath3
* @version 1.0
*/
public class RogueAI extends AI {
    private int firewallProtection;
    private int alertLevel;
    private final int maxAlert;
    /**
    * Returns value of firewallProtection
    *
    * @return firewallProtection
    */
    public int getFirewallProtection() {
        return firewallProtection;
    }
    /**
    * Returns value of alertLevel
    *
    * @return alertLevel
    */
    public int getAlertLevel() {
        return alertLevel;
    }
    /**
    * Returns value of maxAlert
    *
    * @return maxAlert
    */
    public int getMaxAlert() {
        return maxAlert;
    }
    /**
    * Constructor for RogueAI
    *
    * @param firewallProtection int to represent security level of the firewall
    * @param alertLevel         int to represent alert level of computer
    * @param maxAlert           constant level at which computer self destructs
    * @param cannonTarget       coordinates of the cannon's target
    * @param secretHQ           coordinates of the secret HQ
    */
    public RogueAI(int firewallProtection, int alertLevel, int maxAlert,
        Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
        this.firewallProtection = firewallProtection;
        this.alertLevel = alertLevel;
        this.maxAlert = maxAlert;
    }
    /**
    * Constructor for RogueAI (alertLevel = 0)
    *
    * @param firewallProtection int to represent security level of the firewall
    * @param maxAlert           constant level at which computer self destructs
    * @param cannonTarget       coordinates of the cannon's target
    * @param secretHQ           coordinates of the secret HQ
    */
    public RogueAI(int firewallProtection, int maxAlert,
        Coordinates cannonTarget, Coordinates secretHQ) {
        this(firewallProtection, 0, maxAlert, cannonTarget, secretHQ);
    }
    /**
    * Constructor for RogueAI (alertLevel = 0, maxAlert = 10)
    *
    * @param firewallProtection int to represent security level of the firewall
    * @param cannonTarget       coordinates of the cannon's target
    * @param secretHQ           coordinates of the secret HQ
    */
    public RogueAI(int firewallProtection, Coordinates cannonTarget,
        Coordinates secretHQ) {
        this(firewallProtection, 0, 10, cannonTarget, secretHQ);
    }
    /**
    * Decrements firewallProtection by 2
    * Increments alertLevel by 1
    */
    public void lowerFirewall() {
        firewallProtection -= 2;
        alertLevel++;
    }
    /**
    * Validates if cannon can swap target.
    *
    * @return true if firewallProtection <= 0, false otherwise
    */
    @Override
    public boolean shouldSwapCannonTarget() {
        return firewallProtection <= 0;
    }
    /**
    * Validates if AI should self destruct.
    *
    * @return true if alertLevel >= maxAlert, false otherwise
    */
    @Override
    public boolean shouldSelfDestruct() {
        return alertLevel >= maxAlert;
    }
    /**
    * {@inheritDoc}
    */
    @Override
    public String toString() {
        return "Dr. Chipotle’s guacamole cannon is currently pointed at "
            + this.getCannonTarget() + ", and is at alert level "
            + alertLevel + " with firewall protection "
            + firewallProtection + ".";
    }
}
