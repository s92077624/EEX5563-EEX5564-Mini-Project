package test2;

import java.util.ArrayList;
import java.util.List;

public class FirstFit extends MemoryAllocationUI {

    public static void simulateFirstFit() {  // Make this method static
        List<Integer> tempMemorySlots = new ArrayList<>(memorySlots);

        for (int i = 0; i < processSizes.size(); i++) {
            boolean allocated = false;
            for (int j = 0; j < tempMemorySlots.size(); j++) {
                if (tempMemorySlots.get(j) >= processSizes.get(i)) {
                    allocationMap.add("Process " + processSizes.get(i) + " allocated to Block " + (j + 1) + " ("+ memorySlots.get(j) + ")");
                    tempMemorySlots.set(j, tempMemorySlots.get(j) - processSizes.get(i));
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
                allocationMap.add("Process " + (i + 1) + " not allocated");
            }
        }
    }
}
