package bytedance;

/**
 * @author: 詹世雄
 * @date: 2021/3/9 22:47
 * @description:
 */
public class SimplifyUnixPath {

    private static class DeNode {
        String value;
        DeNode pre;
        DeNode next;
        DeNode (String value, DeNode pre) {
            this.value = value;
            this.pre = pre;
        }
    }

    public String simplifyPath(String path) {
        String[] split = path.split("/+");
        DeNode cur = null;
        for (String c : split) {
            if (".".equals(c) || "".equals(c)) {
                continue;
            }
            if ("..".equals(c)) {
                if (cur != null) {
                    cur = cur.pre;
                }
            } else if (cur == null) {
                cur = new DeNode(c, null);
            } else {
                cur.next = new DeNode(c, cur);
                cur = cur.next;
            }
        }
        if (cur == null) {
            return "/";
        } else {
            StringBuilder s = new StringBuilder();
            while (cur.pre != null) {
                s.insert(0, cur.value).insert(0, "/");
                cur = cur.pre;
            }
            s.insert(0, cur.value).insert(0, "/");
            return s.toString();
        }
    }

    public static void main(String[] args) {
        SimplifyUnixPath simplifyUnixPath = new SimplifyUnixPath();
        System.out.println(simplifyUnixPath.simplifyPath("/home//foo/"));
    }

}
