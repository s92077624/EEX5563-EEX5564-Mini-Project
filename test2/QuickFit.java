package test2;

import java.util.HashMap;
import java.util.Map;

public class QuickFit extends MemoryAllocationUI{
    public static void simulateQuickFit() {
        Map<Integer, Integer> quickFitBlocks = new HashMap<>();
        quickFitBlocks.put(100, 3);
        quickFitBlocks.put(200, 2);
        quickFitBlocks.put(300, 1);

        for (int process : processSizes) {
            boolean allocated = false;
            for (Map.Entry<Integer, Integer> entry : quickFitBlocks.entrySet()) {
                if (entry.getKey() >= process && entry.getValue() > 0) {
                    allocationMap.add("Process " + process + " allocated to Block " + entry.getKey());
                    quickFitBlocks.put(entry.getKey(), entry.getValue() - 1);
                    allocated = true;
                    break;
                }
            }
            if (!allocated) {
                allocationMap.add("Process " + process + " not allocated");
            }
        }
    }

}
