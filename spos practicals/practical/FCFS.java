import java.util.Scanner;
// preemptive algo
public class FCFS {
    
    static int process;
    static void priority(int at[],int[] bt,Scanner sc){
        int[] pt=new int[process];
        int[] wt=new int[process];
        int[] tat=new int[process];
        int[] ct=new int[process];
        int[] processes=new int[process];
        for(int i=0;i<process;i++){
             processes[i]=i+1;
            System.out.println("Enter priority for "+(i+1)+" process : ");
            pt[i]=sc.nextInt();
        }
        // step 1: sort priority
        for(int i=0;i<process;i++){
            for(int j=0;j<process-i-1;j++){
                if(pt[j]>pt[j+1]){
                    int temp=at[j];
                    at[j]=at[j+1];
                    at[j+1]=temp;

                    temp=pt[j];
                    pt[j]=pt[j+1];
                    pt[j+1]=temp;

                    temp=bt[j];
                    bt[j]=bt[j+1];
                    bt[j+1]=temp;

                    temp=processes[j];
                    processes[j]=processes[j+1];
                    processes[j+1]=temp;
                }
            }
        }
        
        //step 2 : completion time
        for(int i=0;i<process;i++){
            if(i==0 || at[i]>ct[i-1]){
                ct[i]=at[i]+bt[i];
            }else{
                ct[i]=ct[i-1]+bt[i];
            }
        }
        int avgtat=0;
        int avgwt=0;
        // step 3 : TAT and waiting
        for(int i=0;i<process;i++){
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-bt[i];

            avgtat+=tat[i];
            avgwt+=wt[i];
        }


        System.out.println("Table: ");
        System.out.println("Process \tArrival Time \tBrust time \tCompletion time \tTurnarround time \t waiting timr");
        for(int i=0;i<process;i++){
            System.out.println("\t"+processes[i]+"\t"+at[i]+"\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+tat[i]+"\t\t\t"+wt[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("FCFS");


        System.out.println("Enter number of processes: ");
        process = sc.nextInt();

        int[] at=new int[process];
        int[] wt=new int[process];
        int[] bt= new int[process];
        int[] tat=new int[process];
        int[] ct=new int[process];
        int[] processes=new int[process];
        for(int i=0;i<process;i++){
            processes[i]=i+1;
            System.out.println("Enter arrival time for "+(i+1)+" processes : ");
            at[i]=sc.nextInt();
            System.out.println("Enter brust time for "+(i+1)+" processes : ");
            bt[i]=sc.nextInt();
        }

        // step 1: sort arrival time
        for(int i=0;i<process;i++){
            for(int j=0;j<process-i-1;j++){
                if(at[j]>at[j+1]){
                    int temp=at[j];
                    at[j]=at[j+1];
                    at[j+1]=temp;

                    temp=bt[j];
                    bt[j]=bt[j+1];
                    bt[j+1]=temp;

                    temp=processes[j];
                    processes[j]=processes[j+1];
                    processes[j+1]=temp;
                }
            }
        }

        //step 2 : completion time
        for(int i=0;i<process;i++){
            if(i==0 || at[i]>ct[i-1]){
                ct[i]=at[i]+bt[i];
            }else{
                ct[i]=ct[i-1]+bt[i];
            }
        }
        int avgtat=0;
        int avgwt=0;
        // step 3 : TAT and waiting
        for(int i=0;i<process;i++){
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-bt[i];

            avgtat+=tat[i];
            avgwt+=wt[i];
        }


        System.out.println("Table: ");
        System.out.println("Process \tArrival Time \tBrust time \tCompletion time \tTurnarround time \t waiting timr");
        for(int i=0;i<process;i++){
            System.out.println("\t"+processes[i]+"\t"+at[i]+"\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+tat[i]+"\t\t\t"+wt[i]);
        }
        priority(at,bt,sc);
        sc.close();
    }
}
