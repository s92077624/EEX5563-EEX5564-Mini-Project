package test2;

import java.util.ArrayList;
import java.util.List;

public class NextFit extends MemoryAllocationUI {
    public static void simulateNextFit() {
        List<Integer> tempMemorySlots = new ArrayList<>(memorySlots);
        int lastIndex = 0;

        for (int i = 0; i < processSizes.size(); i++) {
            boolean allocated = false;
            for (int j = 0; j < tempMemorySlots.size(); j++) {
                int currentIndex = (lastIndex + j) % tempMemorySlots.size();
                if (tempMemorySlots.get(currentIndex) >= processSizes.get(i)) {
                    allocationMap.add("Process " + processSizes.get(i) + " allocated to Block " + (currentIndex + 1) + " ("+ memorySlots.get(currentIndex) + ")");
                    tempMemorySlots.set(currentIndex, tempMemorySlots.get(currentIndex) - processSizes.get(i));
                    lastIndex = currentIndex;
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
                allocationMap.add("Process " + processSizes.get(i) + " not allocated");
            }
        }
    }

}
