import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MeetingNode implements Comparable<MeetingNode> {
    int id;
    int startTime;
    int endTime;
    boolean flag;

    public MeetingNode() {
    }

    public MeetingNode(int startTime, int endTime, int id) {

        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }

    static void LongestSelect(ArrayList<MeetingNode> nodes) {
        nodes.get(0).flag = true;
        int j = 0;
        for (int i = 1; i < nodes.size(); i++) {
            if (nodes.get(i).startTime >= nodes.get(j).endTime) {
                nodes.get(i).flag = true;
                j = i;
            } else {
                nodes.get(i).flag = false;
            }
        }
        System.out.println("每次的最大相容子集");
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).flag) {
                System.out.print("编号:" + nodes.get(i).id + "(" + nodes.get(i).startTime + "," + nodes.get(i).endTime + ")");
                nodes.remove(i);
                i--;
            }
        }
        System.out.println();
    }

    @Override
    public int compareTo(MeetingNode o) {
        return this.endTime - o.endTime;
    }

    public static void main(String[] args) {
        ArrayList<MeetingNode> nodes = new ArrayList<>();
        System.out.println("输入总的会议数目:");
        Scanner scanner = new Scanner(System.in);
        Integer n = scanner.nextInt();
        System.out.println("输入活动的开始时间和结束时间:");
        for (int i = 0; i < n; i++) {
            int startTime = scanner.nextInt();
            int endTime = scanner.nextInt();
            nodes.add(new MeetingNode(startTime, endTime, i + 1));
        }
        Collections.sort(nodes);
        for (MeetingNode node : nodes) {
            System.out.println("编号:" + node.id + "(" + node.startTime + "," + node.endTime + ")");
        }
        int count = 0;
        while (!nodes.isEmpty()) {
            LongestSelect(nodes);
            count++;
        }
        System.out.println();
        System.out.println("最少会场数是" + count);


    }


}
