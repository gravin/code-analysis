package com.codeanalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 * 由于是从左往右遍历，所以可用栈保留遍历过的左侧信息，
 * 如果某一元素比左边的元素大，那么可以肯定左边元素一定在其左子树中（可能是子树的子树），那么只比较栈中信息即可
 * 如果某一元素比左边的元素小，那么可以说明该元素是在其左边元素的右子树中(可能是子树的子树)，由于右边信息并未确定，所以无法
 * 根据这情况，构建树
 * 所以构建单调递减的栈，当遇到变大元素时出栈，直到栈空或者维持单调递减才继续
 */

public class Solution {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     * 由于是从左往右遍历，所以可用栈保留遍历过的左侧信息，
     * 如果某一元素比左边的元素大，那么可以肯定左边元素一定在其左子树中（可能是子树的子树），那么只比较栈中信息即可
     * 如果某一元素比左边的元素小，那么可以说明该元素是在其左边元素的右子树中(可能是子树的子树)，由于右边信息并未确定，所以无法
     * 根据这情况，构建树
     * 所以构建单调递减的栈，当遇到变大元素时出栈，直到栈空或者维持单调递减才继续
     */

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, TreeNode> map = new HashMap<>();
        int i = 0;
        while (i < nums.length) {
            if (i == 0 || stack.peek() > nums[i]) {
                TreeNode node = new TreeNode(nums[i]);
                map.put(i, node);
                stack.push(i++);
            } else {
                TreeNode parent = new TreeNode(nums[i]);
                while (!stack.isEmpty() && stack.peek() < nums[i]) {
                    TreeNode node = map.get(stack.pop());
                    if (parent.left == null) {
                        parent.left = node;
                    } else {
                        node.right = parent.left;
                        parent.left = node;
                    }
                }
                map.put(i, parent);
                stack.push(i++);
            }
        }
        TreeNode root = null;
        while (!stack.isEmpty()) {
            if (root != null) {
                TreeNode node = map.get(stack.pop());
                node.right = root;
                root = node;
            } else {
                root = map.get(stack.pop());
            }
        }
        return root;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode treeNode = solution.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5});
        System.out.println(treeNode);
    }
}
