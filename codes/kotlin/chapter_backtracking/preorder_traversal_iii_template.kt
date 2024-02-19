/**
 * File: preorder_traversal_iii_template.kt
 * Created Time: 2024-01-25
 * Author: curtishd (1023632660@qq.com)
 */

package chapter_backtracking

import utils.TreeNode
import utils.printTree
import kotlin.collections.ArrayList

class preorder_traversal_iii_template {
    companion object {
        /* 判断当前状态是否为解 */
        fun isSolution(state: List<TreeNode?>): Boolean {
            return !state.isEmpty() && state[state.size - 1]?.value === 7
        }

        /* 记录解 */
        fun recordSolution(state: MutableList<TreeNode?>?, res: MutableList<List<TreeNode?>?>) {
            res.add(ArrayList(state))
        }

        /* 判断在当前状态下，该选择是否合法 */
        fun isValid(state: List<TreeNode?>?, choice: TreeNode?): Boolean {
            return choice != null && choice.value !== 3
        }

        /* 更新状态 */
        fun makeChoice(state: MutableList<TreeNode?>, choice: TreeNode?) {
            state.add(choice)
        }

        /* 恢复状态 */
        fun undoChoice(state: MutableList<TreeNode?>, choice: TreeNode?) {
            state.removeAt(state.size - 1)
        }

        /* 回溯算法：例题三 */
        fun backtrack(state: MutableList<TreeNode?>, choices: List<TreeNode?>, res: List<List<TreeNode?>?>) {
            // 检查是否为解
            if (isSolution(state)) {
                // 记录解
                recordSolution(state, res.toMutableList())
            }
            // 遍历所有选择
            for (choice in choices) {
                // 剪枝：检查选择是否合法
                if (isValid(state, choice)) {
                    // 尝试：做出选择，更新状态
                    makeChoice(state, choice)
                    // 进行下一轮选择
                    backtrack(state, listOf(choice!!.left, choice.right), res)
                    // 回退：撤销选择，恢复到之前的状态
                    undoChoice(state, choice)
                }
            }
        }
    }
}

/* Driver Code */
fun main() {
    val root = TreeNode.listToTree(mutableListOf(1, 7, 3, 4, 5, 6, 7))
    println("\n初始化二叉树")
    printTree(root)

    // 回溯算法
    val res: List<List<TreeNode?>?> = ArrayList()
    preorder_traversal_iii_template.backtrack(ArrayList(), listOf(root), res)

    println("\n输出所有根节点到节点 7 的路径，要求路径中不包含值为 3 的节点")
    for (path in res) {
        val values: MutableList<Int> = ArrayList()
        for (node in path!!) {
            values.add(node!!.value)
        }
        println(values)
    }
}