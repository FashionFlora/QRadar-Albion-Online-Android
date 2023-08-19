package com.minhui.vpn.Handlers;


import com.minhui.vpn.Handlers.HandlerItem.Mob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MobsHandler {
    private ArrayList<Mob> mobsList;
    private Map<Integer, MobsInfo> mobinfo;

    public void clear() {

        SharedLocks.mobsHandlerLock.writeLock().lock();

        try {

            mobsList.clear();

        } finally {

            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }
    }

    public class MobsInfo
    {

        public int tier = 0;
        public int type = 2;
        public String localization = null;
    }



    public MobsHandler() {
        mobsList = new ArrayList<>();
        mobinfo = new HashMap<>();


        addItem(145, 2, "skinnable", "hide");
        addItem(146, 3, "skinnable", "hide");
        addItem(147, 3, "skinnable", "hide");
        addItem(148, 4, "skinnable", "hide");
        addItem(149, 5, "skinnable", "hide");
        addItem(150, 6, "skinnable", "hide");
        addItem(151, 6, "skinnable", "hide");
        addItem(152, 7, "skinnable", "hide");
        addItem(154, 8, "skinnable", "hide");





        addItem(173, 3, "skinnable", "hide");
        addItem(174, 3, "skinnable", "hide");
        addItem(175, 4, "skinnable", "hide");
        addItem(261, 3, "skinnable", "hide");
        addItem(176, 5, "skinnable", "hide");
        addItem(177, 6, "skinnable", "hide");
        addItem(178, 7, "skinnable", "hide");
        addItem(180, 8, "skinnable", "hide");
        addItem(262, 5, "skinnable", "hide");
        addItem(263, 7, "skinnable", "hide");






        addItem(162, 3, "skinnable", "hide");
        addItem(163, 3, "skinnable", "hide");
        addItem(164, 4, "skinnable", "hide");
        addItem(165, 5, "skinnable", "hide");
        addItem(153, 8, "skinnable", "hide");




        addItem(278, 4, "skinnable", "hide");
        addItem(111, 3, "skinnable", "hide");
        addItem(279, 5, "skinnable", "hide");
        addItem(112, 4, "skinnable", "hide");
        addItem(113, 5, "skinnable", "hide");
        addItem(280, 5, "skinnable", "hide");
        addItem(281, 6, "skinnable", "hide");
        addItem(114, 6, "skinnable", "hide");
        addItem(282, 7, "skinnable", "hide");
        addItem(283, 8, "skinnable", "hide");
        addItem(166, 7, "skinnable", "hide");
        addItem(167, 8, "skinnable", "hide");




        addItem(379, 5, "harvestable", "logs");
        addItem(365, 3, "harvestable", "ore");
        addItem(366, 3, "harvestable", "ore");
        addItem(367, 4, "harvestable", "ore");
        addItem(398, 5, "harvestable", "fiber");
        addItem(399, 6, "harvestable", "fiber");
        addItem(400, 7, "harvestable", "ore");



        addItem(372, 3, "harvestable", "fiber");
        addItem(373, 4, "harvestable", "fiber");
        addItem(374, 5, "harvestable", "fiber");

        addItem(421, 4, "harvestable", "fiber");
        addItem(422, 5, "harvestable", "fiber");
        addItem(423, 6, "harvestable", "fiber");
        addItem(424, 7, "harvestable", "fiber");
        addItem(425, 8, "harvestable", "fiber");
        addItem(260, 7, "harvestable", "fiber");


        addItem(273, 7, "harvestable", "logs");
        addItem(272, 5, "harvestable", "logs");
        addItem(268, 3, "harvestable", "logs");
        addItem(353, 3, "harvestable", "logs");
        addItem(354, 3, "harvestable", "logs");
        addItem(355, 4, "harvestable", "logs");
        addItem(380, 5, "harvestable", "logs");
        addItem(381, 6, "harvestable", "logs");
        addItem(382, 7, "harvestable", "logs");
        addItem(383, 8, "harvestable", "logs");



        addItem(402, 3, "harvestable", "logs");
        addItem(403, 4, "harvestable", "logs");
        addItem(404, 5, "harvestable", "logs");
        addItem(405, 6, "harvestable", "logs");
        addItem(406, 7, "harvestable", "logs");
        addItem(407, 8, "harvestable", "logs");



        addItem(359, 3, "harvestable", "rock");
        addItem(360, 3, "harvestable", "rock");
        addItem(361, 4, "harvestable", "rock");
        addItem(362, 5, "harvestable", "rock");
        addItem(386, 5, "harvestable", "rock");
        addItem(387, 6, "harvestable", "rock");
        addItem(388, 7, "harvestable", "rock");
        addItem(389, 8, "harvestable", "rock");

        // addItem(401, 3, "harvestable", "rock");
        addItem(408, 3, "harvestable", "rock");
        addItem(409, 4, "harvestable", "rock");
        addItem(410, 5, "harvestable", "rock");
        addItem(411, 6, "harvestable", "rock");
        addItem(412, 7, "harvestable", "rock");
        addItem(413, 8, "harvestable", "rock");

        addItem(363, 6, "harvestable", "rock");




        addItem(368, 5, "harvestable", "ore");
        addItem(369, 6, "harvestable", "ore");



        addItem(391, 4, "harvestable", "ore");
        addItem(392, 5, "harvestable", "ore");
        addItem(393, 6, "harvestable", "ore");
        addItem(394, 7, "harvestable", "ore");
        addItem(395, 8, "harvestable", "ore");


        addItem(415, 4, "harvestable", "ore");
        addItem(416, 5, "harvestable", "ore");
        addItem(417, 6, "harvestable", "ore");
        addItem(418, 7, "harvestable", "ore");
        addItem(419, 8, "harvestable", "ore");


        addItem(257, 3, "harvestable", "fiber");
        addItem(258, 3, "harvestable", "fiber");
        addItem(259, 5, "harvestable", "fiber");




        addItem(265, 3, "harvestable", "ore");
        addItem(264, 3, "harvestable", "ore");
        addItem(266, 5, "harvestable", "ore");
        addItem(267, 5, "harvestable", "ore");



        addItem(269, 3, "harvestable", "logs");
        addItem(270, 3, "harvestable", "logs");
        addItem(271, 5, "harvestable", "logs");


        addItem(274, 3, "harvestable", "rock");
        addItem(275, 3, "harvestable", "rock");
        addItem(276, 5, "harvestable", "rock");
        addItem(277, 5, "harvestable", "rock");








        //  addItem(286, 3, "harvestable", "logs");
        addItem(293, 3, "harvestable", "logs");
        addItem(294, 4, "harvestable", "logs");
        addItem(295, 5, "harvestable", "logs");
        addItem(296, 6, "harvestable", "logs");
        addItem(297, 8, "harvestable", "logs");


        addItem(298, 4, "harvestable", "logs");
        addItem(299, 5, "harvestable", "logs");
        addItem(300, 5, "harvestable", "logs");
        addItem(356, 5, "harvestable", "logs");
        addItem(301, 7, "harvestable", "logs");
        addItem(302, 8, "harvestable", "logs");


        //   addItem(301, 3, "harvestable", "rock");
        addItem(308, 3, "harvestable", "rock");
        addItem(309, 4, "harvestable", "rock");
        addItem(310, 5, "harvestable", "rock");
        addItem(311, 6, "harvestable", "rock");
        addItem(312, 8, "harvestable", "rock");
        addItem(385, 5, "harvestable", "rock");


        addItem(313, 4, "harvestable", "rock");
        addItem(314, 5, "harvestable", "rock");
        addItem(315, 5, "harvestable", "rock");
        addItem(316, 6, "harvestable", "rock");
        addItem(317, 8, "harvestable", "rock");



        addItem(323, 3, "harvestable", "ore");
        addItem(324, 4, "harvestable", "ore");
        addItem(325, 5, "harvestable", "ore");
        addItem(326, 6, "harvestable", "ore");
        addItem(327, 7, "harvestable", "ore");
        //   addItem(322, 8, "harvestable", "ore");


        addItem(328, 4, "harvestable", "ore");
        addItem(329, 5, "harvestable", "ore");
        addItem(330, 6, "harvestable", "ore");
        addItem(331, 7, "harvestable", "ore");
        addItem(332, 7, "harvestable", "ore");




        addItem(338, 3, "harvestable", "fiber");
        addItem(339, 4, "harvestable", "fiber");
        addItem(340, 5, "harvestable", "fiber");
        addItem(341, 6, "harvestable", "fiber");
        addItem(342, 8, "harvestable", "fiber");


        addItem(333, 4, "harvestable", "fiber");
        addItem(334, 5, "harvestable", "fiber");
        addItem(335, 6, "harvestable", "fiber");
        addItem(336, 7, "harvestable", "fiber");
        addItem(337, 8, "harvestable", "fiber");
        addItem(397, 5, "harvestable", "fiber");



        addItem(738, 3, "boss", "rabble");
        addItem(765, 3, "boss", "keen");
        addItem(792, 3, "boss", "pyromaniac");
        addItem(820, 3, "boss", "enforcer");
        addItem(740, 6, "boss", "rabble");
        addItem(767, 6, "boss", "keen");
        addItem(794, 6, "boss", "pyromaniac");
        addItem(822, 6, "boss", "enforcer");
        addItem(1717, 5, "boss", "grandfather");
        addItem(1716, 5, "boss", "graybeard");
        addItem(1714, 5, "boss", "maiden");
        addItem(1713, 5, "boss", "leader");
        addItem(1709, 5, "boss", "chief");
        addItem(1708, 5, "boss", "warrior");
        addItem(1721, 5, "boss", "druid");
        addItem(1720, 5, "boss", "elder");
        addItem(1308, 7, "boss", "swordmaster");
        addItem(1335, 7, "boss", "marksman ");
        addItem(1336, 7, "boss", "nameless");
        addItem(1371, 7, "boss", "cryomancer");
        addItem(1389, 7, "boss", "lord");
        addItem(1407, 7, "boss", "mastercryomancer");
        addItem(1337, 8, "boss", "nameless");
        addItem(1372, 8, "boss", "cryomancer");
        addItem(1390, 8, "boss", "lord");
        addItem(1408, 8, "boss", "mastercryomancer");
        addItem(977, 6, "boss", "commander");
        addItem(969, 3, "boss", "lieutenant");
        addItem(1009, 3, "boss", "suppression");
        addItem(1017, 3, "boss", "masterof");
        addItem(1049, 3, "boss", "ritual");
        addItem(1057, 3, "boss", "mistress");
        addItem(1073, 3, "boss", "chosenofmorgana");
        addItem(970, 3, "boss", "lieutenant");
        addItem(974, 3, "boss", "lieutenant");
        addItem(1010, 3, "boss", "suppression");
        addItem(1018, 3, "boss", "masterof");
        addItem(1050, 3, "boss", "ritual");
        addItem(1058, 3, "boss", "mistress");
        addItem(1074, 3, "boss", "chosenofmorgana");
        addItem(971, 3, "boss", "lieutenant");
        addItem(975, 3, "boss", "lieutenant");
        addItem(979, 3, "boss", "commander");
        addItem(1011, 3, "boss", "suppression");
        addItem(1019, 3, "boss", "masterof");
        addItem(1051, 3, "boss", "ritual");
        addItem(1059, 3, "boss", "mistress");
        addItem(1075, 3, "boss", "chosenofmorgana");
        addItem(616, 5, "drone", "droneicon");
        addItem(617, 6, "drone", "droneicon");
        addItem(618, 7, "drone", "droneicon");
        addItem(619, 3, "drone", "droneicon");
    }

    public void addItem(int typeId, int tier, String type, String localization) {

        MobsInfo mobsInfo = new MobsInfo();
        int typeInt;
        switch (type) {
            case "harvestable":
                typeInt = HandlerUtils.HARVESTALBE_MOB;
                break;
            case "skinnable":
                typeInt = HandlerUtils.SKINNABLE_MOB;
                break;
            case "boss":
                typeInt = HandlerUtils.BOSS_MOB;
                break;
            default:
                typeInt = HandlerUtils.DEFAULT_MOB;
                break;
        }
        mobsInfo.tier = tier;
        mobsInfo.type = typeInt;
        mobsInfo.localization = localization;
        mobinfo.put(typeId, mobsInfo);
    }

    public void AddMob(int id, int typeId, float posX, float posY, int health, int enchant, int rarity) {

        SharedLocks.mobsHandlerLock.writeLock().lock();

        try {
            Mob h = new Mob(id, typeId, posX, posY, health, enchant, rarity);

            if (mobinfo.containsKey(typeId)) {
                MobsInfo mobsInfo = mobinfo.get(typeId);
                assert mobsInfo != null;
                h.tier = mobsInfo.tier;
                h.type = mobsInfo.type;
                h.name = mobsInfo.localization;
            }

            if (!mobsList.contains(h)) {
                mobsList.add(h);
            }
        }
        finally {
            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }

    }

    public void RemoveMob(int id) {
        SharedLocks.mobsHandlerLock.writeLock().lock();

        try {
            mobsList.removeIf(x -> x.getId() == id);
        }
        finally {
            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }
    }

    public ArrayList<Mob> getMobList() {

        SharedLocks.mobsHandlerLock.readLock().lock();
        try
        {
            return  new ArrayList<>(mobsList);
        }
        finally {
            SharedLocks.mobsHandlerLock.readLock().unlock();
        }

    }

    public void UpdateMobPosition(int id, float posX, float posY) {

        SharedLocks.mobsHandlerLock.writeLock().lock();

        try {
            for (Mob mob : mobsList) {
                if (mob.getId() == id) {
                    mob.setPosX(posX);
                    mob.setPosY(posY);
                    break;
                }
            }
        } finally {

            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }
    }

    public void UpdateMobEnchantmentLevel(int mobId, int enchantmentLevel) {
        SharedLocks.mobsHandlerLock.writeLock().lock();
        try {
            for (Mob mob : mobsList) {
                if (mob.getId() == mobId) {
                    mob.setEnchantmentLevel(enchantmentLevel);
                    break;
                }
            }
        }finally{

            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }

    }
}