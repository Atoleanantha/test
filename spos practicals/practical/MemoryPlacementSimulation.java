import java.util.Scanner;

class MemoryBlock {
    int id;
    int size;
    boolean allocated;
    int allocatedId = -1;

    public MemoryBlock(int id, int size) {
        this.id = id;
        this.size = size;
        this.allocated = false;
    }
}

public class MemoryPlacementSimulation {

    void firstFit(MemoryBlock[] memoryBlock, MemoryBlock[] processBlock) {
        for (int i = 0; i < processBlock.length; i++) {
            for (int j = 0; j < memoryBlock.length; j++) {
                if (!memoryBlock[j].allocated && processBlock[i].size <= memoryBlock[j].size) {
                    processBlock[i].allocated = true;
                    memoryBlock[j].allocatedId = i;
                    memoryBlock[j].allocated = true; // Set the memory block as allocated
                    System.out.println("process size : "+processBlock[i].size+"  Memory block size: "+memoryBlock[j].size);
                    break;
                }
            }
        }
        for(int i=0;i<processBlock.length;i++){
            if(!processBlock[i].allocated){
                System.out.println("Process size : "+processBlock[i].size + " in Waiting state");
            }
        }
      
    }
    
    void nextFit(MemoryBlock[] memoryBlock,MemoryBlock[] processBlock){
        int continueIdx=0;
        for (int i = 0; i < processBlock.length; i++) {
            for (int j = continueIdx; j < memoryBlock.length; j++) {
                if (!memoryBlock[j].allocated && processBlock[i].size <= memoryBlock[j].size) {
                    processBlock[i].allocated = true;
                    memoryBlock[j].allocatedId = i;
                    memoryBlock[j].allocated = true; // Set the memory block as allocated
                    System.out.println("process size : "+processBlock[i].size+"  Memory block size: "+memoryBlock[j].size);
                    continueIdx=j;
                    break;
                }
            }
            
        }
        for(int i=0;i<processBlock.length;i++){
            if(!processBlock[i].allocated){
                System.out.println("Process size : "+processBlock[i].size + " in Waiting state");
            }
        }
       
    }
    void bestFit(MemoryBlock[] memoryBlock, MemoryBlock[] processBlock) {
        for (int i = 0; i < processBlock.length; i++) {
            int bestFitIdx = -1; // Initialize to an invalid index
            int minDifference = Integer.MAX_VALUE; // Initialize to the maximum possible value

            for (int j = 0; j < memoryBlock.length; j++) {
                int difference = memoryBlock[j].size - processBlock[i].size;

                if (!memoryBlock[j].allocated && processBlock[i].size <= memoryBlock[j].size) {
                    if (difference >= 0 && difference < minDifference) {
                        bestFitIdx = j;
                        minDifference = difference;
                    }
                }
            }

            if (bestFitIdx != -1) {
                processBlock[i].allocated = true;
                memoryBlock[bestFitIdx].allocatedId = i;
                memoryBlock[bestFitIdx].allocated = true;
                System.out.println("Process size : " + processBlock[i].size + "  Memory block size: " + memoryBlock[bestFitIdx].size);
            }
        }

        for (int i = 0; i < processBlock.length; i++) {
            if (!processBlock[i].allocated) {
                System.out.println("Process size : " + processBlock[i].size + " in Waiting state");
            }
        }
    }
    void worstFit(MemoryBlock[] memoryBlock, MemoryBlock[] processBlock) {
        for (int i = 0; i < processBlock.length; i++) {
            int worstFitIdx = -1; // Initialize to an invalid index
            int maxDifference = Integer.MIN_VALUE; // Initialize to the maximum possible value

            for (int j = 0; j < memoryBlock.length; j++) {
                int difference = memoryBlock[j].size - processBlock[i].size;

                if (!memoryBlock[j].allocated && processBlock[i].size <= memoryBlock[j].size) {
                    if (difference >= 0 && difference > maxDifference) {
                        worstFitIdx = j;
                        maxDifference = difference;
                    }
                }
            }

            if (worstFitIdx != -1) {
                processBlock[i].allocated = true;
                memoryBlock[worstFitIdx].allocatedId = i;
                memoryBlock[worstFitIdx].allocated = true;
                System.out.println("Process size : " + processBlock[i].size + "  Memory block size: " + memoryBlock[worstFitIdx].size);
            }
        }

        for (int i = 0; i < processBlock.length; i++) {
            if (!processBlock[i].allocated) {
                System.out.println("Process size : " + processBlock[i].size + " in Waiting state");
            }
        }
    }
    public static void main(String[] args) {
        MemoryPlacementSimulation mPlacementSimulation = new MemoryPlacementSimulation();
        Scanner sc = new Scanner(System.in);

        // for memory blocks
        System.out.print("Enter number of memory blocks: ");
        int blocks = sc.nextInt();
        MemoryBlock memoryBlock[] = new MemoryBlock[blocks];
        for (int i = 0; i < blocks; i++) {
            System.out.print("Enter size of memory block " + (i + 1) + ": ");
            memoryBlock[i] = new MemoryBlock(i, sc.nextInt());
        }

        // for processes
        System.out.print("Enter number of processes: ");
        int processes = sc.nextInt();
        MemoryBlock processBlock[] = new MemoryBlock[processes];
        for (int i = 0; i < processes; i++) {
            System.out.print("Enter size of process " + (i + 1) + ": ");
            processBlock[i] = new MemoryBlock(i, sc.nextInt());
        }

        System.out.println("Choose option: \n1)First fit \n2)Next fit \n3)Best fit \n4)Worst fit \n0)Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                mPlacementSimulation.firstFit(memoryBlock, processBlock);
                break;
            case 2:
                mPlacementSimulation.nextFit(memoryBlock, processBlock);
                break;
            case 3:
                mPlacementSimulation.bestFit(memoryBlock, processBlock);
                break;
            case 4:
                mPlacementSimulation.worstFit(memoryBlock, processBlock);
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
        }
    }
}
