package test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuddySystem extends MemoryAllocationUI{
	public static void simulateBuddySystem() {
	    List<Integer> buddyBlocks = new ArrayList<>(Arrays.asList(512, 256, 128, 64, 32));

	    for (int process : processSizes) {
	        boolean allocated = false;
	        for (int i = 0; i < buddyBlocks.size(); i++) {
	            int block = buddyBlocks.get(i);
	            // Find the smallest block that fits the process size
	            while (block / 2 >= process && block / 2 >= 32) { // Ensure minimum block size is 32
	                block /= 2;
	            }
	            if (block >= process) {
	                allocationMap.add("Process " + process + " allocated to Block " + block);
	                buddyBlocks.set(i, block - process); // Update the current block
	                if (block > process) {
	                    buddyBlocks.add(i + 1, block - process); // Add the remaining buddy
	                }
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
