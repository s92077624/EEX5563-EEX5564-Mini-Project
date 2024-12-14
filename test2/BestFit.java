package test2;

import java.util.ArrayList;
import java.util.List;

public class BestFit extends MemoryAllocationUI{
    public static void simulateBestFit() {
        List<Integer> tempMemorySlots = new ArrayList<>(memorySlots);

        for (int i = 0; i < processSizes.size(); i++) {
            int bestIdx = -1;
            for (int j = 0; j < tempMemorySlots.size(); j++) {
                if (tempMemorySlots.get(j) >= processSizes.get(i)) {
                    if (bestIdx == -1 || tempMemorySlots.get(j) < tempMemorySlots.get(bestIdx)) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocationMap.add("Process " + processSizes.get(i) + " allocated to Block " + (bestIdx + 1) +" ("+ memorySlots.get(bestIdx) + ")");
                tempMemorySlots.set(bestIdx, tempMemorySlots.get(bestIdx) - processSizes.get(i));
            } else {
                allocationMap.add("Process " + (i + 1) + " not allocated");
            }
        }
    }

}
