package fun;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 有三扇紧闭的门，其中一扇门后面是汽车，另外两扇门后面都是不值钱的山羊。
 * 假设你是参赛者，你选定一扇门后（还不知道结果）主持人都会打开了另外一扇有山羊的门。
 * 随后会问你要不要换另一扇门。你会换吗？
 */
public class ThreeDoor {
    public double test(int doorSize, int count, boolean exchange) {
        Random random = new Random();
        int rightSize = 0;
        for (int i = 0; i < count; i++) {
            // 准备好游戏
            List<Integer> idxs = new LinkedList<>();
            for (int idx = 0; idx < doorSize; idx++) {
                idxs.add(idx);
            }
            int rightIdx = random.nextInt(doorSize);
            // 选择
            int chooseIdx = random.nextInt(doorSize);
            // 交换
            if (exchange) {
                int remCount = 0;
                while (remCount < doorSize - 2) {
                    int rem = random.nextInt(idxs.size());
                    int remIdx = idxs.get(rem);
                    if (remIdx != chooseIdx && remIdx != rightIdx) {
                        idxs.remove(rem);
                        remCount++;
                    }
                }
                int a = idxs.get(0);
                int b = idxs.get(1);
                chooseIdx = chooseIdx != a ? a : b;
            }
            if (chooseIdx == rightIdx) {
                rightSize += 1;
            }
        }
        return (double) rightSize / count;
    }

    public static void main(String[] args) {
        ThreeDoor threeDoor = new ThreeDoor();
        System.out.println(threeDoor.test(3, 100000, true));
    }
}