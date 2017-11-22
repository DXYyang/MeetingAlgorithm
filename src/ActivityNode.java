import java.util.*;

public class ActivityNode implements Comparable<ActivityNode> {
    int timeStamp;
    boolean flag;//0开始时间,1结束时间

    public ActivityNode() {
    }

    public ActivityNode(int timeStamp, boolean flag) {
        this.timeStamp = timeStamp;
        this.flag = flag;
    }

    @Override
    public int compareTo(ActivityNode o) {
        return this.timeStamp - o.timeStamp;
    }

    static void merge(ActivityNode nodes[], int left, int mid, int right) {
        int i = left, j = mid + 1, k = 0;
        ActivityNode temp[] = new ActivityNode[right - left + 1];
        while (i <= mid && j <= right) {
            if (nodes[i].timeStamp < nodes[j].timeStamp) temp[k++] = nodes[i++];
            else temp[k++] = nodes[j++];
        }
        while (i <= mid) temp[k++] = nodes[i++];
        while (j <= right) temp[k++] = nodes[j++];
        System.arraycopy(temp, 0, nodes, left, temp.length);
    }

    static void mergeSort(ActivityNode nodes[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(nodes, left, mid);
            mergeSort(nodes, mid + 1, right);
            merge(nodes, left, mid, right);
        }
    }

    static int leastMeetingCount(ActivityNode nodes[], int n) {
        int count = 0;
        int maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nodes[i].flag) {
                count++;
                if (count > maxCount) maxCount = count;
            } else count--;
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println("输入总的会议数目:");
        Scanner scanner = new Scanner(System.in);
        Integer n = scanner.nextInt();
        ActivityNode startNodes[] = new ActivityNode[n];
        ActivityNode endNodes[] = new ActivityNode[n];

        System.out.println("输入活动的开始时间:");
        for (int i = 0; i < n; i++) {
            startNodes[i] = new ActivityNode();
            startNodes[i].timeStamp = scanner.nextInt();
        }
        System.out.println("输入活动的结束时间:");
        for (int i = 0; i < n; i++) {
            endNodes[i] = new ActivityNode();
            endNodes[i].timeStamp = scanner.nextInt();

        }
        for (int i = 0; i < n; i++) {
            startNodes[i].flag = true;
            endNodes[i].flag = false;
        }
        Arrays.sort(startNodes);
        Arrays.sort(endNodes);
        ActivityNode totalMeeting[] = new ActivityNode[2 * n];
        System.arraycopy(startNodes, 0, totalMeeting, 0, n);
        System.arraycopy(endNodes, 0, totalMeeting, n, n);
        ActivityNode.mergeSort(totalMeeting, 0, 2 * n - 1);
        int result = leastMeetingCount(totalMeeting, 2 * n);
        System.out.println("最少会场数为:" + result);
    }


}
