import java.util.ArrayList;
import java.util.Scanner;

/**
 * PageReplacement
 */
public class PageReplacement {
    boolean contains(int ele,int[] pageFrame){
        for(int i=0;i<pageFrame.length;i++){
            if(ele==pageFrame[i]){
                return true;
            }
        }
        return false;
    }
    void printResult(int pageFault,int pageHits){
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Total Faults = "+ pageFault);
        System.out.println("Total Hits = "+ pageHits);
        System.out.println("----------------------------------------------------------------------------");
    }

    void printFrame(int []pageFrame,String type){
        for(int i=0;i<pageFrame.length;i++){
            System.out.print(pageFrame[i]+" | ");
        }
        System.out.println(type);
    }

    void fifo(ArrayList<Integer> streamArray, int[] pageFrame,int length){
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("FIFO Page Replacement");
        System.out.println("----------------------------------------------------------------------------");
        int pageHit=0,pageFault=0;
        int trackIdx=0;
        int pgFrameIdx=0;
        while (trackIdx<length) {
            
            while(pgFrameIdx<pageFrame.length && trackIdx<length){
                if(contains((int)streamArray.get(trackIdx), pageFrame)){
                    pageHit++;
                    trackIdx++;
                    printFrame(pageFrame, "Hit");
                    
                }
                else{
                    pageFrame[pgFrameIdx]=(int)streamArray.get(trackIdx);
                    pgFrameIdx++;
                    pageFault++;
                    trackIdx++;

                    printFrame(pageFrame, "Fault");
                    
                }
            }
            pgFrameIdx=0;
            if(trackIdx==length){
                break;
            }
        }
        printResult(pageFault, pageHit);
        }

    void lru(ArrayList<Integer> streamArray, int[] pageFrame, int length) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("LRU Page Replacement");
        System.out.println("----------------------------------------------------------------------------");
        int pageHit = 0, pageFault = 0;
        int trackIdx = 0;
        int pgFrameIdx = 0;
    
        while (trackIdx < length) {
            
                if (contains(streamArray.get(trackIdx), pageFrame)) {
                    // Page hit
                    pageHit++;
                    trackIdx++;
                    printFrame(pageFrame, "Hit");
                } else {
                    // Page fault
                    if(trackIdx<pageFrame.length){
                        pageFrame[pgFrameIdx]=streamArray.get(trackIdx);

                        printFrame(pageFrame, "Fault");
                        pageFault++;
                        trackIdx++;
                        pgFrameIdx++;
                        continue;
                    }
                    int leastRecentlyUsedIdx = streamArray.size();
                    int temp = trackIdx;
    
                    for (int i = 0; i < pageFrame.length; i++) {
                        // Reset temp to the original value of trackIdx
                        temp = trackIdx;
    
                        while (temp >= 0) {
                            if (streamArray.get(temp).equals(pageFrame[i]) && temp < leastRecentlyUsedIdx) {
                                leastRecentlyUsedIdx = temp;
                                break;
                            }
                            temp--;
                        }
                    }                    
                    pageFrame[leastRecentlyUsedIdx] = streamArray.get(trackIdx);
                    pageFault++;
                    printFrame(pageFrame, "Fault");
                    pgFrameIdx++;
                }
                trackIdx++;
            
            if (trackIdx == length) {
                break;
            }
        }
        printResult(pageFault, pageHit);
    
    }

    void optimal(ArrayList<Integer> streamArray, int[] pageFrame, int length) {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Optimal Page Replacement");
        System.out.println("----------------------------------------------------------------------------");
        int pageHit = 0, pageFault = 0;
        int trackIdx = 0;
        int pgFrameIdx = 0;
        for(int i=0;i<pageFrame.length;i++){
            pageFrame[i]=-1;
        }
        while (trackIdx < length) {
            
                if (contains(streamArray.get(trackIdx), pageFrame)) {
                    // Page hit
                    pageHit++;
                    trackIdx++;
                    printFrame(pageFrame, "Hit");
                } else {
                    // Page fault

                    if(pgFrameIdx <pageFrame.length){
                        pageFrame[pgFrameIdx]=streamArray.get(trackIdx);
                        trackIdx++;
                        pageFault++;
                        pgFrameIdx++;
                        printFrame(pageFrame, "Fault");
                        
                        continue;
                    }
                    int idx=0;
                    int e=0;
                    for(int i=0;i<pageFrame.length;i++){
                        int temp=trackIdx;
                        while (temp<length) {
                            if(temp>idx &&  streamArray.get(temp).equals(pageFrame[i])){
                                idx=temp;
                                break;
                            }else if(streamArray.get(temp).equals(pageFrame[i])){
                                break;
                            }else if(temp ==length-1 && !streamArray.get(temp).equals(pageFrame[i])){
                                idx=length;
                                e=i;
                                i=pageFrame.length;
                                break;
                            }
                            temp++;
                        }
                    }
                    if(idx==length){
                        idx=e;
                    }else{
                        idx=streamArray.get(idx);
                        for(int i=0;i<pageFrame.length;i++){
                        if(idx==pageFrame[i]){
                            idx=i;
                            break;
                        }
                    }
                    }
                    
                    
                    pageFrame[idx]=streamArray.get(trackIdx);
                    pageFault++;
                    trackIdx++;
                    printFrame(pageFrame, "Fault");
                }
            
            if (trackIdx == length) {
                break;
            }
        }
        printResult(pageFault, pageHit);
    
    }

    
    
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.print("Enter frame size : ");
        int fSize=sc.nextInt();
        
        int[] pageFrame=new int[fSize];
        for(int i=0;i<fSize;i++){
            pageFrame[i]=-1;
        }

        System.out.println("Enter page stream: ");
        String streamString=sc.next();

        //convert string into int array
        ArrayList<Integer> streamArray=new ArrayList<>(streamString.length());
        System.out.println(streamArray.size());
        for(int i=0;i<streamString.length();i++){
            streamArray.add(Integer.parseInt(streamString.substring(i, i+1)));
        }
        PageReplacement obj=new PageReplacement();

        System.out.println("Choose option: \n1) FIFO Page Replacement \n2)LRU \n3)Optimal Page Replacement \n0)Exit");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                    obj.fifo(streamArray, pageFrame, streamString.length());
                    break;
            case 2:obj.lru(streamArray, pageFrame, streamString.length());
                break;
            case 3:obj.optimal(streamArray, pageFrame, streamString.length());
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                break;
        }
        sc.close();
        
    }
}
// eg: 70120304230321201701