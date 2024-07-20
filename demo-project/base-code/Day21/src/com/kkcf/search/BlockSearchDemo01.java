package com.kkcf.search;


public class BlockSearchDemo01 {
    private static class Block {
        private int max;
        private int startIndex;
        private int endIndex;

        public Block() {
        }

        public Block(int max, int startIndex, int endIndex) {
            this.max = max;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }
    }
    /**
     * 此方法用于，在索引表中，查找元素所在块的索引
     *
     * @param blockArr 索引表
     * @param target   目标元素
     * @return 所在块的索引
     */
    private static int findIndexBlock(Block[] blockArr, int target) {
        for (int i = 0; i < blockArr.length; i++)
            if (target < blockArr[i].getMax())
                return i;

        return -1;
    }

    /**
     * 此方法用于，在数组中，找到目标元素的索引
     * @param blockArr 索引表
     * @param arr 数组
     * @param target 目标元素
     * @return 目标元素的索引
     */
    private static int getIndex(Block[] blockArr, int[] arr, int target) {
        int indexBlock = findIndexBlock(blockArr, target);

        if (indexBlock == -1)
            return indexBlock;

        Block targetBlock = blockArr[indexBlock];
        int startIndex = targetBlock.getStartIndex();
        int endIndex = targetBlock.getEndIndex();

        // 这里可用顺序查找，或者二分查找实现，下面使用的是顺序查找
        for (int i = startIndex; i <= endIndex; i++)
            if (arr[i] == target)
                return i;


        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {16, 5, 9, 12, 21, 18,
                32, 23, 37, 26, 45, 34,
                50, 48, 61, 52, 73, 66};

        Block b1 = new Block(21, 0, 5);
        Block b2 = new Block(45, 6, 11);
        Block b3 = new Block(73, 12, 17);

        Block[] blockArr = {b1, b2, b3};

        int target = 12;

        int index = getIndex(blockArr, arr, target);

        System.out.println(index == -1 ? "未找到该元素" : "该元素在数组中的索引为：" + index);
    }
}
