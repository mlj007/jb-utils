package com.jb.utils.algorithm;
/**
 * 【八大内部排序算法】
 * 01-插入排序
 *    直接插入排序
 *    希尔排序
 * 02-选择排序
 *    简单选择排序
 *    堆排序
 * 03-交换排序
 *    冒泡排序
 *    快速排序
 * 04-归并排序
 * 05-基数排序
 * @see https://blog.csdn.net/weixin_43022263/article/details/105563381
 */
public class EightKindsSort {
    /**
     * 直接插入排序<br>
     * arr[0]做监视哨，每轮排序先将待插入的记录存入arr[0]，然后将该关键字插入已经有序的一组记录中<br>
     * 注意数组中第0个记录不会计入排序<br>
     * 时间复杂度O(n^2)，空间复杂度O(1)，稳定排序，注意排序结果不包括arr[0]
     * @param arr 待排序的数组
     */
    public static void straightInsertSort(int arr[]) {
        int j;
        for(int i = 1; i < arr.length; i++) {
            arr[0] = arr[i];
            for(j = i-1; j>=0 && arr[j]>arr[0]; j--)
                arr[j+1] = arr[j];
            arr[j+1] = arr[0];
        }
    }
    /**
     * 折半插入排序<br>
     * 大致操作同上面的折半插入，不同的只是查找插入位置时不再是<br>
     * 一个一个查找比较，而是采用二分查找到应该插入位置，然后再统一移动<br>
     * 所以该方法只是减少了关键字之间的比较次数，而记录的移动次数并未改变,arr[0]做监视哨<br>
     * 时间复杂度O(n^2)，空间复杂度O(1)，稳定排序，注意排序结果不包括arr[0]
     * @param arr 待排序的数组
     */
    public static void binaryInsertSort(int arr[]) {
        int mid, l, r;
        for(int i = 2; i < arr.length; i++) {
            arr[0] = arr[i];
            l = 1; r = i-1;
            while(l <= r) {//查找待插入的位置
                mid = (l+r) / 2;
                if(arr[0] < arr[mid])
                    r = mid - 1;
                else l = mid + 1;
            }
            for(int j = i-1; j >=l; j--)
                arr[j+1] = arr[j];
            arr[l] = arr[0];
        }
    }
    /**
     * 希尔排序又称缩小增量排序，是插入排序的一种<br>
     * 先将待排序的记录序列按增量分割成几组，从而减少参与直接插入排序的数据量,arr[0]做监视哨<br>
     * 选取合适的增量序列时，时间复杂度O(n^(3/2))，空间复杂度O(1)，不稳定排序，注意排序结果不包括arr[0]
     * @param arr 待排序数组
     * @param dt 增量数组
     */
    public static void shellSort(int arr[], int dt[]) {
        for(int i = 0; i < dt.length; i++)
            shellInsert(arr, dt[i]);
    }
    public static void shellInsert(int arr[], int dt) {
        int j ;
        for(int i = dt+1; i < arr.length; i++) {
            arr[0] = arr[i];
            for(j = i-dt; j>=0 && arr[j]>arr[0]; j -= dt)
                arr[j+dt] = arr[j];
            arr[j+dt] = arr[0];
        }
    }
    /**
     * 冒泡排序<br>
     * 将整个记录序列两两进行比较，关键字大的交换至右边，小的交换至左边<br>
     * 时间复杂度O(n^2)，空间复杂度O(1)，稳定排序
     * @param arr 待排序数组
     */
    public static void bubbleSort(int arr[]) {
        int temp, len=arr.length-1;
        boolean flag = true;
        while(len>0 && flag) {
            flag = false;
            for(int i = 1; i <= len; i++) {
                if(arr[i] < arr[i-1]) {
                    temp = arr[i];
                    arr[i] = arr[i-1];
                    arr[i-1] = temp;
                    flag = true;
                }
            }
            len--;
        }
    }
    /**
     * 快速排序<br>
     * 由冒泡排序改进而来，一次交换可以消除多个逆序，而冒泡一次交换只可以消除一个逆序<br>
     * 平均时间复杂度O(nlogn) ，最好情况下的空间复杂度O(logn)，最坏为O(n)<br>
     * 不稳定的排序 ，适合初始记录无序，n较大的情况
     * @param arr 待排序数组
     * @param l 待排序区间的左边界
     * @param r 待排序区间的右边界（包括在内）
     */
    public static void quickSort(int arr[], int l, int r) {
        int key = arr[l], i=l, j=r;
        while(i < j) {
            while(i<j && arr[j]>=key)
                j--;
            arr[i] = arr[j];
            while(i<j && arr[i]<=key)
                i++;
            arr[j] = arr[i];
        }
        arr[i] = key;//将关键字key填入它应该在的位置
        if(i > l)
            quickSort(arr, l, i-1);
        if(i < r)
            quickSort(arr, i+1, r);
    }
    /*************************堆排序***************************/
    /**
     * 堆排序<br>
     * 排序期间维护一个大根堆（小根堆）,即若一个结点为根结点，则其左右子树的所有结点都小于（大于）根结点<br>
     * 每一轮排序将树根元素与未经排序子序列的尾部元素进行交换，然后再将除尾部元素外的元素调整为堆<br>
     * 时间复杂度 O(nlogn)，空间复杂度O(1)，不稳定排序，当记录较多时较为高效<br>
     * 最坏情况下时间复杂度为O(nlogn)，相对于快排最坏情况下的O(n^2)要好， 注意排序不包括0号元素
     * @param arr 待排序数组
     */
    public static void heapSort(int arr[]) {
        createHeap(arr);//建初堆
        int temp;
        for(int i = arr.length-1; i > 1; i--) {
            temp = arr[i];
            arr[i] = arr[1];//每次将堆顶元素与未经排序的子序列arr[1..i]中最后一个记录互换
            arr[1] = temp;
            heapAdjust(arr, 1, i-1);//缩小堆的大小，重新调整为大根堆
        }
    }
    /**
     * 建立初堆<br>
     * 堆排序生成的二叉树看成是一个完全二叉树，而在完全二叉树中所有序号大于n/2的结点都是叶子结点<br>
     * 对于只有一个结点的树必然是一个堆，所以可以认为所有叶子结点都已经是一个堆<br>
     * 所以只需从最后一个非叶子结点n/2开始，从序号为n/2，n/2-1，n/2-2....1的结点作为根的子树调整为堆即可<br>
     *  注意排序不包括0号元素
     * @param arr 待排序数组
     */
    static void createHeap(int arr[]) {
        for(int i = arr.length/2; i > 0; i--)
            heapAdjust(arr, i, arr.length-1);
    }
    /**
     * arr[l+1....r]已经是一个大顶堆，方法作用的目的就是将arr[l...r]调整为以arr[l]为根的大顶堆
     * @param arr 待排序数组
     * @param l 表示待调整的元素下标
     * @param r 表示大顶堆的最后一个元素
     */
    static void heapAdjust(int arr[], int l, int r) {
        int temp = arr[l], i;//首先将arr[l]元素暂存至temp中，i用于存两个孩子结点中较大结点的下标
        for(i = 2*l; i <= r; i *= 2) {//沿元素值较大的孩子结点向下筛选
            if(i<r && arr[i]<arr[i+1]) //找出l结点的两个孩子结点中较大的元素的下标
                i += 1;
            if(temp > arr[i])//若待插入元素temp比孩子结点都大，则l即为temp应该插入的位置
                break;
            arr[l] = arr[i];//temp小于孩子结点中较大的元素，则将孩子元素交换至l处
            l = i;          //l下移至刚刚那个孩子的位置
        }
        arr[l] = temp;
    }
    /************************************************************/
    /**
     * 简单选择排序<br>
     * 每一趟排序从无序序列中选出关键字最小的放在已排序的记录序列的最后面<br>
     * 时间复杂度O(n^2)，空间复杂度O(1)，稳定排序（但如果采取的交换策略不同，可能会不稳定）
     * @param arr 待排序数组
     */
    public static void selectSort(int arr[]) {
        int temp;
        for(int i = 0; i < arr.length; i++) {
            int sub = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[sub] > arr[j])
                    sub = j;//保存最小值的下标
            }
            temp = arr[i];
            arr[i] = arr[sub];
            arr[sub] = temp;
        }
    }
    /**
     * 归并排序<br>
     * 通过将两个或两个以上的有序表合并成一个有序表的过程。<br>
     * 假设初始序列有n个记录，此时每个记录可以看成是一个有序的序列，且每个序列 <br>
     * 的长度为1，然后两两归并，得到n/2个长度为2的或1的有序序列。再两两归并...如此重复，<br>
     * 直至得到一个长度为n的有序序列为止，这是很显然的一个递归思想<br>
     * 时间复杂度O(nlogn)，空间复杂度O(n)，稳定排序
     * @param arr 待排序数组
     * @param t 需要用到的辅助数组
     * @param l 待排序区间的左边界（包括）
     * @param r 待排序区间的右边界（包括）
     */
    public static void mergeSort(int arr[], int t[], int l, int r) {
        if(l < r) {
            int mid = (l+r) / 2;
            mergeSort(arr, t, l, mid);
            mergeSort(arr, t, mid+1, r);
            //合并两个有序区间(因为上两个递归结束后，此时左右两半应该是有序了的)
            int i = l, j = mid+1, k=l;
            while(i<=mid && j<=r) { //将两个有序列表中较小的放入t中
                if(arr[i] <= arr[j])
                    t[k++] = arr[i++];
                else t[k++] = arr[j++];
            }
            while(i <= mid)//将剩余的arr[i..mid]复制到t中
                t[k++] = arr[i++];
            while(j <= r)//将剩余的arr[j..r]复制到t中
                t[k++] = arr[j++];
            for(i = l; i <= r; i++)//将t[l..r]中的部分复制到arr中对应部分
                arr[i] = t[i];
        }
    }
    /**
     * 数组打印方法<br>
     * @param arr 待打印的数组
     * @param l 待打印区间的左边界
     * @param r 待打印区间的右边界（不包括）
     * @param type 排序类型
     */
    public static void print(int arr[], int l, int r, int type) {
        String s = null;
        switch(type) {
            case 1: s = "直接插入排序：" ; break;
            case 2: s = "折半插入排序：" ; break;
            case 3: s = "希尔排序：" ; break;
            case 4: s = "冒泡排序：" ; break;
            case 5: s = "快速排序：" ; break;
            case 6: s = "简单选择排序：" ; break;
            case 7: s = "堆排序：" ; break;
            case 8: s = "归并排序：" ; break;
        }
        System.out.print(s);
        for(int i = l ; i < r; i++)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    public static void main(String []args) {
        /*************插入排序****************/
        //01-直接插入排序
        int arr1[] = {0,2,5,6,123,-12,324,5};
        straightInsertSort(arr1);
        print(arr1, 1, arr1.length, 1);
        //02-折半插入排序
        int arr2[] = {0,2,5,6,123,-12,324,5};
        binaryInsertSort(arr2);
        print(arr2, 1, arr2.length, 2);
        //03-希尔排序
        int dt[] = {5,3,1};
        int arr3[] = {0,2,5,6,123,-12,324,5};
        shellSort(arr3, dt);
        print(arr3, 1, arr3.length, 3);

        /**********交换排序*****************/
        //04-冒泡排序
        int arr4[] = {0,2,5,6,123,-12,324,5};
        bubbleSort(arr4);
        print(arr4, 0, arr4.length, 4);
        //05-快速排序
        int arr5[] = {0,2,5,6,123,-12,324,5};
        quickSort(arr5, 0, arr5.length-1);
        print(arr5, 0, arr5.length, 5);
        /***********选择排序******************/
        //06-简单选择排序
        int arr6[] = {0,2,5,6,123,-12,324,5};
        selectSort(arr6);
        print(arr6, 0, arr6.length, 6);
        //07-堆排序
        int arr7[] = {0,2,5,6,123,-12,324,5};
        heapSort(arr7);
        print(arr7, 1, arr7.length, 7);
        //08-树形选择排序。。不会写
        /*************归并排序*****************/
        int arr8[] = {0,2,5,6,123,-12,324,5};
        int t[] = new int[arr8.length];
        mergeSort(arr8, t, 0, arr8.length-1);
        print(arr8, 0, arr8.length, 8);
        /******基数排序，通过若干躺“分配”和“收集”来实现，一般采用链式存储***/
    }
}
