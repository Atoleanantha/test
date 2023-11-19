import java.util.*;

class Process {
    int processID;
    int arrivalTime, waitingTime, completionTime, burstTime, turnaroundTime, remainingTime;
    int at;
    int flag;
}

public class RR {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n = sc.nextInt();

        Process[] p = new Process[n];
        Queue<Integer> q = new LinkedList<Integer>();

        int sumOfbt = 0, qt;

        // Initialize each element of the array
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
        }

        for (int i = 0; i < n; i++) {
            p[i].processID = i + 1;
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            p[i].arrivalTime = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            p[i].burstTime = sc.nextInt();
            p[i].remainingTime = p[i].burstTime;
            p[i].flag = 0;
            sumOfbt += p[i].burstTime;
        }

        System.out.print("Enter quantum time: ");
        qt = sc.nextInt();

        // Sort based on arrival time
        Arrays.sort(p, Comparator.comparingInt(process -> process.arrivalTime));

        q.add(0);
        int time = p[0].arrivalTime;

        while (time < sumOfbt) {
            int i = q.remove();

            if (p[i].remainingTime <= qt) {
                time += p[i].remainingTime;
                p[i].remainingTime = 0;
                p[i].flag = 1;
                p[i].completionTime = time;
                p[i].waitingTime = time - p[i].arrivalTime - p[i].burstTime;
                p[i].turnaroundTime = time - p[i].arrivalTime;

                for (int j = 0; j < n; j++) {
                    if (p[j].flag == 0 && p[j].arrivalTime <= time && !q.contains(j)) {
                        q.add(j);
                    }
                }
            } else {
                time += qt;
                p[i].remainingTime -= qt;

                for (int j = 0; j < n; j++) {
                    if (p[j].flag == 0 && p[j].arrivalTime <= time && !q.contains(j)) {
                        q.add(j);
                    }
                }
                q.add(i);
            }
        }

        System.out.println("ID\tArrival\tBurst\tCompletion\tTurnaround\tWaiting");
        for (int i = 0; i < n; i++) {
            System.out.println(p[i].processID + "\t" + p[i].arrivalTime + "\t" + p[i].burstTime + "\t"
                    + p[i].completionTime + "\t" + p[i].turnaroundTime + "\t" + p[i].waitingTime);
        }
    }
}
