/**
* AI superclass
*
* The foundation for any AI that DoctorCS creates.
*
* @author jmcmath3
* @version 1.0
*/
public abstract class AI {
    private boolean destructed;
    private Coordinates cannonTarget;
    private Coordinates secretHQ;
    /**
    * Returns value of destructed
    *
    * @return destructed
    */
    public boolean getDestructed() {
        return destructed;
    }
    /**
    * Returns value of cannonTarget
    *
    * @return cannonTarget
    */
    public Coordinates getCannonTarget() {
        return cannonTarget;
    }
    /**
    * Returns value of secretHQ
    *
    * @return secretHQ
    */
    public Coordinates getSecretHQ() {
        return secretHQ;
    }
    /**
    * Constructor for AI
    *
    * @param cannonTarget coordinates of the cannon's target
    * @param secretHQ     coordinates of the secret HQ
    */
    public AI(Coordinates cannonTarget, Coordinates secretHQ) {
        this.cannonTarget = cannonTarget;
        this.secretHQ = secretHQ;
    }
    /**
    * Attempts to change the cannonTarget field
    *
    * @param newCoords new coordinates to be targeted
    * @return true if target was swapped, false otherwise
    */
    public boolean swapCannonTarget(Coordinates newCoords) {
        if (!(getDestructed()
              || newCoords.equals(getCannonTarget()))) {
            if (shouldSwapCannonTarget()) {
                cannonTarget = newCoords;
                return true;
            } else if (shouldSelfDestruct()) {
                selfDestruct();
            }
        }
        return false;
    }
    /**
    * Checks if the cannon should swap targets
    *
    * @return true if the cannon should swap, false otherwise
    */
    public abstract boolean shouldSwapCannonTarget();
    /**
    * Checks if the AI should self destruct
    *
    * @return true if the AI should self destruct, false otherwise
    */
    public abstract boolean shouldSelfDestruct();
    /**
    * Self detructs the AI instance.
    * Sets destructed to true.
    */
    public void selfDestruct() {
        destructed = true;
    }
    /**
    * {@inheritDoc}
    */
    @Override
    public String toString() {
        return "Dr. Chipotle’s guacamole cannon is currently pointed at "
                + cannonTarget.toString() + ".";
    }
}
