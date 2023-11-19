import java.util.*;

// Process class to represent each process
class Process {
    int processID;      // Process ID
    int arrival, burst, waiting, turnAround, remainingTime;   // Arrival time, Burst time, Waiting time, Turnaround time, Remaining time
    int finish, completionTime;  // Finish flag, Completion time
}

public class RRScheduling {

    public static void main(String[] args) {
        // Variables for input and calculation
        int n, sumBurst = 0, quantum, time;
        double avgWAT = 0, avgTAT = 0;

        // Scanner for user input
        Scanner sc = new Scanner(System.in);
        
        // Queue for ready processes
        Queue<Integer> q = new LinkedList<>();

        // Introduction
        System.out.println("*** RR Scheduling (Preemptive) ***");
        System.out.print("Enter Number of Process: ");
        n = sc.nextInt();

        // Array to store processes
        Process[] p = new Process[n];

        // Input each process details
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].processID = i + 1;
            System.out.print("Enter the arrival time for P" + (i + 1) + ": ");
            p[i].arrival = sc.nextInt();
            System.out.print("Enter the burst time for P" + (i + 1) + ": ");
            p[i].burst = sc.nextInt();
            p[i].remainingTime = p[i].burst;
            p[i].finish = 0;
            sumBurst += p[i].burst;
            System.out.println();
        }

        // Input time quantum
        System.out.print("\nEnter time quantum: ");
        quantum = sc.nextInt();

        // Sort processes based on arrival time
        Process pTemp;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (p[i].arrival > p[j].arrival) {
                    pTemp = p[i];
                    p[i] = p[j];
                    p[j] = pTemp;
                }
            }
        }

        // Initialize the queue with the first process
        q.add(0);

        // Loop until all processes are executed
        for (time = p[0].arrival; time < sumBurst;) {
            Integer I = q.remove();
            int i = I.intValue();

            // If remaining time is less than or equal to the quantum
            if (p[i].remainingTime <= quantum) {
                time += p[i].remainingTime;
                p[i].remainingTime = 0;
                p[i].finish = 1;
                p[i].completionTime = time;
                p[i].waiting = time - p[i].arrival - p[i].burst;
                p[i].turnAround = time - p[i].arrival;

                // Add newly arrived processes to the queue
                for (int j = 0; j < n; j++) {
                    Integer J = Integer.valueOf(j);
                    if ((p[j].arrival <= time) && (p[j].finish != 1) && (!q.contains(J)))
                        q.add(j);
                }
            } else {
                // If remaining time is more than the quantum
                time += quantum;
                p[i].remainingTime -= quantum;

                // Add newly arrived processes (except the current one) to the queue
                for (int j = 0; j < n; j++) {
                    Integer J = Integer.valueOf(j);
                    if (p[j].arrival <= time && p[j].finish != 1 && i != j && (!q.contains(J)))
                        q.add(j);
                }

                // Add the current process back to the queue
                q.add(i);
            }
        }

        // Output results
        System.out.println("\n*** RR Scheduling (Preemptive) ***");
        System.out.println("Processor\tArrival time\tBrust time\tCompletion Time\t\tTurn around time\tWaiting time");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + p[i].processID + "\t\t" + p[i].arrival + "\t\t" + p[i].burst + "\t\t"
                    + p[i].completionTime + "\t\t\t" + p[i].turnAround + "\t\t\t" + p[i].waiting + "");
            avgWAT += p[i].waiting;
            avgTAT += p[i].turnAround;
        }

        // Output average turnaround time and waiting time
        System.out.println("\nAverage turn around time of processor: " + (avgTAT / n)
                + "\nAverage waiting time of processor: " + (avgWAT / n) + "");
    }
}
