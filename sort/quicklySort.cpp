void quicklySort(int *nums, int len)
{
    sort(nums, 0, len);
}

void sort(int *nums, int left, int right)
{
    while (left < right)
    {
        int mid = (left + right) / 2;
        mid = handle(nums, left, right, mid);
        sort(nums, left, mid);
        sort(nums, mid + 1, right);
    }
}

int handle(int *nums, int left, int right, int key)
{
    int val = nums[key];
    while (left < right)
    {
        while (left < right && nums[right] > val)
            right--;
        while (left < right && nums[left] < val)
            left++;
        if (left < right)
        {
            swap(nums, left, right);
        }
    }
    nums[key] = nums[left];
    nums[left] = val;
    return left;
}

void swap(int *nums, int idx1, int idx2)
{
    int temp = nums[idx1];
    nums[idx1] = nums[idx2];
    nums[idx2] = temp;
}