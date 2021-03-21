package algorithms.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 詹世雄
 * @date: 2021/3/20 20:32
 * @description: 93.复原 IP 地址 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 示例 3：
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 *
 * 示例 4：
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 *
 * 示例 5：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *  
 * 提示：
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 */
public class Problem93 {

    /**
     * DFS一颗树，根节点为原始串，高度固定，所有路径的子串和 = 根串；每个节点满足 0 <= n <= 255，且 0 < n 时不能以0开头。
     * 节点剩余长度：根 <= 12，1级节点 <= 9，2级节点 <= 6，3级节点 <= 3，4级节点 = 0
     * 终止：能满足到4级节点的条件
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> ips = new ArrayList<>();
        dfsTree(s, 0, 0, 0, new String[4], ips);
        return ips;
    }

    private static void dfsTree(String  root, int i, int j, int deep, String[] ip, List<String> ips) {
        if (j > root.length()) {
            return;
        }
        if ((root.length() - j) > (12 - 3 * deep)) {
            return;
        }
        if (deep > 0) {
            String cur = root.substring(i, j);
            if (cur.length() > 1 && (cur.charAt(0) == '0' || Integer.parseInt(cur) > 255)) {
                return;
            }
            ip[deep - 1] = cur;
            if (deep == 4) {
                ips.add(ip[0].concat(".").concat(ip[1]).concat(".").concat(ip[2]).concat(".").concat(ip[3]));
            }
        }
        i = j;
        deep++;
        dfsTree(root, i, j + 1, deep, ip, ips);
        dfsTree(root, i, j + 2, deep, ip, ips);
        dfsTree(root, i, j + 3, deep, ip, ips);
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("0000"));
        System.out.println(restoreIpAddresses("1111"));
        System.out.println(restoreIpAddresses("010010"));
        System.out.println(restoreIpAddresses("101023"));
    }

}
