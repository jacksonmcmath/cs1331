import java.util.Random;

/**
 * VendingMachine class
 *
 * @version 1.0
 * @author jmcmath3
 */
public class VendingMachine {
    private static double totalSales;
    private VendingItem[][][] shelf;
    private int luckyChance;
    private Random rand = new Random();
    /**
     * VendingMachine constructor
     */
    public VendingMachine() {
        this.luckyChance = 0;
        this.shelf = new VendingItem[6][3][5];
        restock();
    }
    /**
     * Vend an item from the VendingMachine
     *
     * @param code the code typed into the VendingMachine (e.g "A3")
     *
     * @return a VendingItem from the position determined by the code if the
     * code is valid, null otherwise
     */
    public VendingItem vend(String code) {
        if (!code.toLowerCase().matches("([a-f][1-3])|([A-F][1-3])")) {
            System.out.println("Whoops! That code is invalid...");
            return null;
        }
        int row = (int) code.toLowerCase().charAt(0) - 'a';
        int col = Character.getNumericValue(code.charAt(1)) - 1;
        VendingItem item = shelf[row][col][0];
        for (int i = 0; i < 5; i++) {
            if (i == 4) {
                shelf[row][col][i] = null;
            } else {
                shelf[row][col][i] = shelf[row][col][i + 1];
            }
        }
        if (null == item) {
            System.out.println("Whoops! That position is out of stock...");
            return null;
        } else {
            if (free()) {
                System.out.println("Wow! You just got a free item! Congrats!");
                return item;
            } else {
                totalSales += item.getPrice();
                return item;
            }
        }
    }
    /**
     * Helper method for vend. Checks if the item being vended will be free.
     *
     * @return true if the item is free, false otherwise
     */
    private boolean free() {
        if (rand.nextInt(100) < luckyChance) {
            luckyChance = 0;
            return true;
        } else {
            luckyChance++;
            return false;
        }
    }
    /**
     * Completely refills the VendingMachine's shelf.
     */
    public void restock() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 5; k++) {
                    shelf[i][j][k] = VendingItem.values()[
                        rand.nextInt(VendingItem.values().length)
                    ];
                }
            }
        }
    }
    /**
     * Getter for totalSales.
     *
     * @return the totalSales across all VendingMachine instances
     */
    public static double getTotalSales() {
        return totalSales;
    }
    /**
     * Getter for the total number of VendingItems on this VendingMachine's
     * shelf.
     *
     * @return the total number of items in this VendingMachine instance
     */
    public int getNumberOfItems() {
        int itemCount = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 5; k++) {
                    if (null != shelf[i][j][k]) {
                        itemCount++;
                    }
                }
            }
        }
        return itemCount;
    }
    /**
     * Getter for the total price of this VendingMachine's shelf.
     *
     * @return the total price of everything on this VendingMachine's shelf
     */
    public double getTotalValue() {
        double totalValue = 0.0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 5; k++) {
                    if (null == shelf[i][j][k]) {
                        continue;
                    } else {
                        totalValue += shelf[i][j][k].getPrice();
                    }
                }
            }
        }
        return totalValue;
    }
    /**
     * Getter for luckyChance for this VendingMachine.
     *
     * @return the value of luckyChance for this VendingMachine
     */
    public int getLuckyChance() {
        return luckyChance;
    }
    /**
     * Creates a String representation for this VendingMachine.
     *
     * @return the new String representation for this VendingMachine
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append("                            VendaTron 9000                "
            + "            \n");
        for (int i = 0; i < shelf.length; i++) {
            s.append("------------------------------------------------------"
                + "----------------\n");
            for (int j = 0; j < shelf[0].length; j++) {
                VendingItem item = shelf[i][j][0];
                String str = String.format("| %-20s ",
                    (item == null ? "(empty)" : item.name()));
                s.append(str);
            }
            s.append("|\n");
        }
        s.append("----------------------------------------------------------"
            + "------------\n");
        s.append(String.format("There are %d items with a total "
            + "value of $%.2f.%n", getNumberOfItems(), getTotalValue()));
        s.append(String.format("Total sales across vending machines "
            + "is now: $%.2f.%n", getTotalSales()));
        return s.toString();
    }
}
