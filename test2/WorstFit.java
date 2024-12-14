package test2;

import java.util.ArrayList;
import java.util.List;

public class WorstFit extends MemoryAllocationUI{
    public static void simulateWorstFit() {
        List<Integer> tempMemorySlots = new ArrayList<>(memorySlots);

        for (int i = 0; i < processSizes.size(); i++) {
            int worstIdx = -1;
            for (int j = 0; j < tempMemorySlots.size(); j++) {
                if (tempMemorySlots.get(j) >= processSizes.get(i)) {
                    if (worstIdx == -1 || tempMemorySlots.get(j) > tempMemorySlots.get(worstIdx)) {
                        worstIdx = j;
                    }
                }
            }

            if (worstIdx != -1) {
                allocationMap.add("Process " + processSizes.get(i) + " allocated to Block " + (worstIdx + 1) + " ("+ memorySlots.get(worstIdx) + ")");
                tempMemorySlots.set(worstIdx, tempMemorySlots.get(worstIdx) - processSizes.get(i));
            } else {
                allocationMap.add("Process " + (i + 1) + " not allocated");
            }
        }
    }

}
