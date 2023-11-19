import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SJF");

        int process;
        System.out.println("Enter number of processes: ");
        process = sc.nextInt();

        int[] at=new int[process];
        int[] wt=new int[process];
        int[] bt= new int[process];
        int[] tat=new int[process];
        int[] ct=new int[process];
        int[] processes=new int[process];
        int[] flag=new int[process];
        int[] bt1=new int[process];

        for(int i=0;i<process;i++){
            processes[i]=i+1;
            System.out.println("Enter arrival time for "+(i+1)+" processes : ");
            at[i]=sc.nextInt();
            System.out.println("Enter brust time for "+(i+1)+" processes : ");
            bt[i]=sc.nextInt();
            bt1[i]=bt[i];

            flag[i]=0;
        }
        int completed=0 ,st=0;//st:start
       
        while(completed==process){
            int min=999,currentProcessIndex=process;

            //find short process
            for(int i=0;i<process;i++){
                if(at[i]<=st && flag[i]==0 && bt[i]<min){
                    min=bt[i];
                    currentProcessIndex=i;
                }
            }

            if(currentProcessIndex==process){
                st++;//completion time of each process at that time
            }else{
                bt[currentProcessIndex]--;
                st++;
                if(bt[currentProcessIndex]==0){
                    completed++;
                    ct[currentProcessIndex]=st;
                    flag[currentProcessIndex]=1;
                }
            }
        }
        int avgtat=0,avgwt=0;
        //tat and wt calculation
        for(int i=0;i<process;i++){
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-bt1[i];

            avgtat +=tat[i];
            avgwt += wt[i];
        }

        System.out.println("\nPID  Arrival  Burst  Complete  Turnaround  Waiting");
        for (int i = 0; i < process; i++) {
            System.out.println(processes[i] + "\t" + at[i] + "\t" + bt1[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + (float) (avgtat / process));
        System.out.println("Average Waiting Time: " + (float) (avgwt / process));
        sc.close();

    }
}
