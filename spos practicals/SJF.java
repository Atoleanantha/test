import java.util.Scanner;

public class SJF {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Shortest Job First (SJF) Scheduling Algorithm");

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        // Arrays to store process information
        int pid[] = new int[n]; // Process ID
        int at[] = new int[n]; // Arrival Time
        int bt[] = new int[n]; // Burst Time
        int ct[] = new int[n]; // Completion Time
        int ta[] = new int[n]; // Turnaround Time
        int wt[] = new int[n]; // Waiting Time
        int f[] = new int[n];  // Flag to check if process is completed or not
        int k[] = new int[n];  // Stores burst time

        int i, st = 0, tot = 0;
        float avgwt = 0, avgta = 0;

        // Input process details
        for (i = 0; i < n; i++) {
            pid[i] = i + 1;
            System.out.print("Enter process " + (i + 1) + " arrival time: ");
            at[i] = sc.nextInt();
            System.out.print("Enter process " + (i + 1) + " burst time: ");
            bt[i] = sc.nextInt();
            k[i] = bt[i];  // Initialize k array with burst time
            f[i] = 0;      // Initialize flag array to 0
        }

        // SJF Scheduling Algorithm
        while (true) {
            int min = 99, c = n;
            if (tot == n)
                break;

            // Find the process with the shortest burst time at this moment
            for (i = 0; i < n; i++) {
                if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                    min = bt[i];
                    c = i;  // Store the index of the process with the shortest burst time
                }
            }

            // If no process is ready to execute, increment the time
            if (c == n)
                st++;
            else {
                bt[c]--;  // Execute the process for one unit of time
                st++;
                if (bt[c] == 0) {
                    ct[c] = st;  // Set completion time for the process
                    f[c] = 1;    // Set flag to indicate process completion
                    tot++;      // Increment total completed processes
                }
            }
        }

        // Calculate Turnaround Time and Waiting Time
        for (i = 0; i < n; i++) {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i];
            avgwt += wt[i];
            avgta += ta[i];
        }

        // Display process details and average times
        System.out.println("\nPID  Arrival  Burst  Complete  Turnaround  Waiting");
        for (i = 0; i < n; i++) {
            System.out.println(pid[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + (float) (avgta / n));
        System.out.println("Average Waiting Time: " + (float) (avgwt / n));
        sc.close();
    }
}
