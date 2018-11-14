package org.xiafei.spring.cloud;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class UserNameService {
    private final Map<String, String> userNameMap = new HashMap<>();
    private final String names = "刀白凤 丁春秋 马夫人 马五德 小翠 于光豪 巴天石 不平道人 邓百川 风波恶 甘宝宝 公冶乾 木婉清 少林老僧 " +
            "太皇太后 天狼子 天山童姥 王语嫣 乌老大 无崖子 云岛主 云中鹤 止清 白世镜 包不同 本参 本观 本相 本因 出尘子 " +
            "冯阿三 兰剑 古笃诚 过彦之 平婆婆 石清露 石嫂 司空玄 司马林 玄慈 玄寂 玄苦 玄难 玄生 玄痛 叶二娘 竹剑 左子穆 " +
            "华赫艮 乔峰 李春来 李傀儡 李秋水 刘竹庄 朴者和尚 祁六三 全冠清 阮星竹 西夏宫女 许卓诚 朱丹臣 努儿海 阿碧 阿洪 " +
            "阿胜 阿朱 阿紫 波罗星 陈孤雁 鸠摩智 来福儿 孟师叔 宋长老 苏星河 苏辙 完颜阿古打 耶律洪基 耶律莫哥 耶律涅鲁古 耶律重元" +
            " 吴长风 吴光胜 吴领军 辛双清 严妈妈 余婆婆 岳老三 张全祥 单伯山 单季山 单叔山 单小山 单正 段延庆 段誉 段正淳 段正明 " +
            "范禹 范百龄 范骅 苟读 和里布 何望海 易大彪 郁光标 卓不凡 宗赞王子 哈大霸 姜师叔 枯荣长老 梦姑 姚伯当 神山上人 神音 " +
            "狮鼻子 室里 项长老 幽草 赵钱孙 赵洵 哲罗星 钟灵 钟万仇 高升泰 龚光杰 贾老者 康广陵 秦红棉 虚竹 容子矩 桑土公 唐光雄 " +
            "奚长老 徐长老 诸保昆 崔百泉 崔绿华 符敏仪 黄眉和尚 菊剑 聋哑婆婆 梅剑 萧远山 游骥 游驹 游坦之 程青霜 傅思归 葛光佩 " +
            "缘根 智光大师 鲍千灵 褚万里 瑞婆婆 端木元 黎夫人 薛慕华 慕容博 慕容复 谭公 谭婆 谭青 摘星子 慧方 慧观 慧净 慧真 穆贵妃 " +
            "赫连铁树 一灯大师 马青雄 马钰 小沙弥 木华黎 丘处机 沈青刚 书记 书生 天竺僧人 王处一 王罕 尹志平 包惜弱 冯衡 术赤 农夫 " +
            "孙不二 札木合 华筝 李萍 刘玄处 刘瑛姑 吕文德 乔寨主 曲三 曲傻姑 全金发 汤祖德 朱聪 陈玄风 赤老温 瘦丐 陆乘风 陆冠英 沙通天" +
            " 吴青烈 杨康 杨铁心 余兆兴 张阿生 张十五 忽都虎 欧阳峰 欧阳克 梅超风 铁木真 拖雷 者勒米 段天德 枯木 周伯通 郭靖 郭啸天 郝大通 洪七公 侯通海 姜文 柯镇恶 南希仁 胖妇人 胖丐 胖子 哑梢公 都史 钱青健 桑昆 盖运聪 黄蓉 黄药师 梁长老 梁子翁 渔人 博尔忽 博尔术 程瑶迦 韩宝驹 韩小莹 焦木和尚 鲁有脚 穆念慈 彭长老 彭连虎 童子 窝阔台 简长老 简管家 裘千仞 裘千丈 察合台 酸儒文人 谭处端 黎生 樵子 灵智上人 完颜洪烈 完颜洪熙";
    private final String[] nameRepo;

    public UserNameService() {
        nameRepo = this.names.split(" ");
    }

    void removeName(WebSocketSession webSocketSession) {
        userNameMap.remove(webSocketSession.getId());
    }

    String getUserName(WebSocketSession webSocketSession) {
        String name = userNameMap.get(webSocketSession.getId());
        if (name == null) {
            name = randomName();
            userNameMap.put(webSocketSession.getId(), name);
        }
        return name;
    }

    private String randomName() {
        return nameRepo[new Random().nextInt(nameRepo.length)];
    }
}
