/**
 * 1. Using instanceof is bad because it means you're probably downcasting,
 * which is bad.
 * 2. We could give the AI superclass the abstract methods involving the
 * firewall and define them differently for the RandomAI class.
 */

/**
* DoctorCS class
*
* Our Hero! This class has an AI to be used to destroy Dr. Guacamole and a
* secret identity that is only known by this class.
*
* @author jmcmath3
* @version 1.0
*/
public class DoctorCS {
    private AI ai;
    private String secretIdentity;
    private int jlaid;
    private boolean safe;
    /**
    * Returns value of ai
    *
    * @return ai
    */
    public AI getAI() {
        return ai;
    }
    /**
    * Returns value of jlaid
    *
    * @return jlaid
    */
    public int getJlaid() {
        return jlaid;
    }
    /**
    * Constructor for RogueAI
    *
    * @param ai             AI used to fight Dr. Chipotle
    * @param secretIdentity String for DoctorCS' secret identity
    * @param jlaid          int for DoctorCS' Justice League of America ID
    */
    public DoctorCS(AI ai, String secretIdentity, int jlaid) {
        this.ai = ai;
        this.secretIdentity = secretIdentity;
        this.jlaid = jlaid;
    }
    /**
    *
    */
    public void saveTheDay() {
        if (ai instanceof RogueAI) {
            while (((RogueAI) ai).getFirewallProtection() > 0) {
                ((RogueAI) ai).lowerFirewall();
            }
        }
        safe = ai.swapCannonTarget(ai.getSecretHQ());
    }
    /**
    * Checks the status of DoctorCS' fight against Dr. Chipotle
    *
    * @return String containing the status
    */
    public String getStatus() {
        if (safe) {
            return "Doctor CS has saved the day!";
        } else {
            if (ai.getDestructed()) {
                return "Dr. Chipotle has succeeded in his plan...";
            } else {
                return "Georgia Tech is still in danger!";
            }
        }
    }
    /**
    * {@inheritDoc}
    */
    public String toString() {
        return secretIdentity + " aka Doctor CS with JLAID: " + jlaid;
    }
}
