import java.util.Random;
/**
* RandomAI class, extending AI
*
* This class is not very smart. when you tell it to swapCannonTarget(), there is
* a 50% chance that it will and a 50% chance that it won't. If it doesn't swap
* the target, there's a 50% chance that it will self desrtuct.
*
* @author jmcmath3
* @version 1.0
*/
public class RandomAI extends AI {
    private static final Random randomizer = new Random();
    /**
    * Constructor for RandomAI
    *
    * @param cannonTarget coordinates of the cannon's target
    * @param secretHQ     coordinates of the secret HQ
    */
    public RandomAI(Coordinates cannonTarget, Coordinates secretHQ) {
        super(cannonTarget, secretHQ);
    }
    /**
    * Checks if cannon can swap target.
    *
    * @return true 50% of the time, false 50% of the time
    */
    @Override
    public boolean shouldSwapCannonTarget() {
        return randomizer.nextBoolean();
    }
    /**
    * Checks if AI should selfDestruct.
    *
    * @return true 50% of the time, false 50% of the time
    */
    @Override
    public boolean shouldSelfDestruct() {
        return randomizer.nextBoolean();
    }
}
