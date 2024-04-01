package com.minhui.vpn.Handlers;
import android.util.Log;
import com.google.gson.Gson;
import com.minhui.vpn.Handlers.HandlerItem.Mob;
import com.minhui.vpn.PhotonPackageParser.enumerations.MobCodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobsHandler
{
    final Gson gson = new Gson();
    private ArrayList<Mob> mobsList;
    private Map<Integer, MobsInfo> mobinfo;

    public void clear()
    {
        SharedLocks.mobsHandlerLock.writeLock().lock();

        try
        {
            mobsList.clear();
        }
        finally
        {
            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }
    }

    public class MobsInfo
    {
        public int tier = 0;
        public MobCodes type = MobCodes.Enemy;
        public String localization = null;
    }

    public MobsHandler()
    {
        mobsList = new ArrayList<>();
        mobinfo = new HashMap<>();

        // ----------- Hide --------- //

        addItem(111, 3, MobCodes.Skinnable, "hide");
        addItem(112, 4, MobCodes.Skinnable, "hide");
        addItem(113, 5, MobCodes.Skinnable, "hide");
        addItem(114, 6, MobCodes.Skinnable, "hide");

        addItem(145, 2, MobCodes.Skinnable, "hide");
        addItem(146, 3, MobCodes.Skinnable, "hide");
        addItem(147, 3, MobCodes.Skinnable, "hide");
        addItem(148, 4, MobCodes.Skinnable, "hide");
        addItem(149, 5, MobCodes.Skinnable, "hide");
        addItem(150, 6, MobCodes.Skinnable, "hide");
        addItem(151, 6, MobCodes.Skinnable, "hide");
        addItem(152, 7, MobCodes.Skinnable, "hide");
        addItem(153, 8, MobCodes.Skinnable, "hide");
        addItem(154, 8, MobCodes.Skinnable, "hide");

        addItem(162, 3, MobCodes.Skinnable, "hide");
        addItem(163, 3, MobCodes.Skinnable, "hide");
        addItem(164, 4, MobCodes.Skinnable, "hide");
        addItem(165, 5, MobCodes.Skinnable, "hide");
        addItem(166, 7, MobCodes.Skinnable, "hide");
        addItem(167, 8, MobCodes.Skinnable, "hide");

        addItem(173, 3, MobCodes.Skinnable, "hide");
        addItem(174, 3, MobCodes.Skinnable, "hide");
        addItem(175, 4, MobCodes.Skinnable, "hide");
        addItem(176, 5, MobCodes.Skinnable, "hide");
        addItem(177, 6, MobCodes.Skinnable, "hide");
        addItem(178, 7, MobCodes.Skinnable, "hide");
        addItem(180, 8, MobCodes.Skinnable, "hide");

        addItem(261, 3, MobCodes.Skinnable, "hide");
        addItem(262, 5, MobCodes.Skinnable, "hide");
        addItem(263, 7, MobCodes.Skinnable, "hide");

        addItem(278, 4, MobCodes.Skinnable, "hide");
        addItem(279, 5, MobCodes.Skinnable, "hide");

        addItem(280, 1, MobCodes.Skinnable, "hide");
        addItem(281, 6, MobCodes.Skinnable, "hide");
        addItem(282, 7, MobCodes.Skinnable, "hide");
        addItem(283, 8, MobCodes.Skinnable, "hide");

        // Mists +25
        addItem(286, 1, MobCodes.Skinnable, "hide"); // WOLPERTINGER
        addItem(287, 2, MobCodes.Skinnable, "hide"); // FOX
        addItem(288, 3, MobCodes.Skinnable, "hide"); // DEER
        addItem(289, 4, MobCodes.Skinnable, "hide"); // GIANTSTAG
        addItem(290, 5, MobCodes.Skinnable, "hide"); // OWL
        addItem(291, 6, MobCodes.Skinnable, "hide"); // HOUND
        addItem(292, 7, MobCodes.Skinnable, "hide"); // DIREBEAR
        addItem(293, 8, MobCodes.Skinnable, "hide"); // DRAGONHAWK

        // Roads +25
        addItem(314, 1, MobCodes.Skinnable, "hide"); // SALAMANDER
        addItem(315, 3, MobCodes.Skinnable, "hide"); // STAG
        addItem(316, 4, MobCodes.Skinnable, "hide"); // DIREWOLF
        addItem(317, 5, MobCodes.Skinnable, "hide"); // BISON
        addItem(318, 6, MobCodes.Skinnable, "hide"); // OWL
        addItem(319, 7, MobCodes.Skinnable, "hide"); // DIREBEAR
        addItem(320, 8, MobCodes.Skinnable, "hide"); // BASILISK

        // Forest biome + 15
        addItem(321, 1, MobCodes.Skinnable, "hide"); // Rabbit
        addItem(322, 2, MobCodes.Skinnable, "hide"); // Fox
        addItem(323, 3, MobCodes.Skinnable, "hide"); // Fox tutorial

        // +16
        addItem(324, 3, MobCodes.Skinnable, "hide"); // Wolf
        addItem(325, 4, MobCodes.Skinnable, "hide"); // Boar
        addItem(326, 5, MobCodes.Skinnable, "hide"); // Bear
        addItem(327, 6, MobCodes.Skinnable, "hide"); // Direwolf
        addItem(328, 6, MobCodes.Skinnable, "hide"); // Giant Stag
        addItem(329, 7, MobCodes.Skinnable, "hide"); // Direboar small
        addItem(330, 7, MobCodes.Skinnable, "hide"); // Direboar
        addItem(331, 8, MobCodes.Skinnable, "hide"); // Direbear small
        addItem(332, 8, MobCodes.Skinnable, "hide"); // Direbear

        // Swamp biome +25
        addItem(338, 1, MobCodes.Skinnable, "hide");
        addItem(339, 2, MobCodes.Skinnable, "hide");
        addItem(340, 3, MobCodes.Skinnable, "hide");
        addItem(341, 4, MobCodes.Skinnable, "hide");
        addItem(342, 5, MobCodes.Skinnable, "hide");
        addItem(343, 6, MobCodes.Skinnable, "hide"); // Dragon
        addItem(344, 7, MobCodes.Skinnable, "hide"); // Marabou
        addItem(345, 8, MobCodes.Skinnable, "hide"); // Alligator

        // Steppe biome +25
        addItem(349, 1, MobCodes.Skinnable, "hide"); // Marmot
        addItem(350, 2, MobCodes.Skinnable, "hide"); // Impala
        addItem(351, 3, MobCodes.Skinnable, "hide"); // Moabird
        addItem(352, 4, MobCodes.Skinnable, "hide"); // Giant stag
        addItem(353, 5, MobCodes.Skinnable, "hide"); // Terrorbird
        addItem(354, 6, MobCodes.Skinnable, "hide"); // Hyena
        addItem(355, 7, MobCodes.Skinnable, "hide"); // Rhino
        addItem(356, 7, MobCodes.Skinnable, "hide"); // Bighorn Rhino
        addItem(357, 8, MobCodes.Skinnable, "hide"); // Mammoth
        addItem(358, 8, MobCodes.Skinnable, "hide"); // Ancient Mammoth
        addItem(438, 3, MobCodes.Skinnable, "hide"); // Cougar
        addItem(439, 5, MobCodes.Skinnable, "hide"); // Cougar
        addItem(440, 7, MobCodes.Skinnable, "hide"); // Cougar

        // Highland
        addItem(364, 1, MobCodes.Skinnable, "hide"); // Rabbit
        addItem(365, 1, MobCodes.Skinnable, "hide"); // Marmot

        addItem(438, 3, MobCodes.Skinnable, "hide");
        addItem(448, 5, MobCodes.Skinnable, "hide");

        // Cougars +25
        addItem(456, 4, MobCodes.Skinnable, "hide");
        addItem(457, 5, MobCodes.Skinnable, "hide");
        addItem(458, 6, MobCodes.Skinnable, "hide");
        addItem(459, 7, MobCodes.Skinnable, "hide");
        addItem(460, 8, MobCodes.Skinnable, "hide");

        // Veteran
        addItem(461, 4, MobCodes.Skinnable, "hide");
        addItem(462, 5, MobCodes.Skinnable, "hide");
        addItem(463, 6, MobCodes.Skinnable, "hide");
        addItem(464, 7, MobCodes.Skinnable, "hide");
        addItem(465, 8, MobCodes.Skinnable, "hide");

        // Elite
        addItem(466, 4, MobCodes.Skinnable, "hide");
        addItem(467, 5, MobCodes.Skinnable, "hide");
        addItem(468, 6, MobCodes.Skinnable, "hide");
        addItem(469, 7, MobCodes.Skinnable, "hide");
        addItem(470, 8, MobCodes.Skinnable, "hide");

        addItem(1265, 6, MobCodes.Skinnable, "hide");
        addItem(1438, 3, MobCodes.Skinnable, "hide");

        // ----------- Fiber --------- //

        addItem(257, 3, MobCodes.Harvestable, "fiber");
        addItem(258, 3, MobCodes.Harvestable, "fiber");
        addItem(259, 5, MobCodes.Harvestable, "fiber");
        addItem(260, 7, MobCodes.Harvestable, "fiber");

        addItem(333, 4, MobCodes.Harvestable, "fiber");
        addItem(334, 5, MobCodes.Harvestable, "fiber");
        addItem(335, 6, MobCodes.Harvestable, "fiber");
        addItem(336, 7, MobCodes.Harvestable, "fiber");
        addItem(337, 8, MobCodes.Harvestable, "fiber");

        addItem(372, 3, MobCodes.Harvestable, "fiber");
        addItem(373, 4, MobCodes.Harvestable, "fiber");
        addItem(374, 5, MobCodes.Harvestable, "fiber");

        addItem(397, 5, MobCodes.Harvestable, "fiber");
        addItem(398, 5, MobCodes.Harvestable, "fiber");
        addItem(399, 6, MobCodes.Harvestable, "fiber");

        addItem(421, 4, MobCodes.Harvestable, "fiber");
        addItem(422, 5, MobCodes.Harvestable, "fiber");
        addItem(423, 6, MobCodes.Harvestable, "fiber");
        addItem(424, 7, MobCodes.Harvestable, "fiber");
        addItem(425, 8, MobCodes.Harvestable, "fiber");

        // ??? <=> Don't know the location <=> Tx_MOB_CRITTER_FIBER + 25
        addItem(435, 3, MobCodes.Harvestable, "fiber");
        addItem(436, 4, MobCodes.Harvestable, "fiber");
        addItem(437, 5, MobCodes.Harvestable, "fiber");

        // Roads <=> Tx_MOB_CRITTER_FIBER_ROADS +25
        addItem(516, 4, MobCodes.Harvestable, "fiber");
        addItem(517, 5, MobCodes.Harvestable, "fiber");
        addItem(518, 6, MobCodes.Harvestable, "fiber");
        addItem(519, 7, MobCodes.Harvestable, "fiber");
        addItem(520, 8, MobCodes.Harvestable, "fiber");
        
        // Veteran Roads <=> Tx_MOB_CRITTER_FIBER_ROADS_VETERAN +25
        addItem(521, 4, MobCodes.Harvestable, "fiber");
        addItem(522, 5, MobCodes.Harvestable, "fiber");
        addItem(523, 6, MobCodes.Harvestable, "fiber");
        addItem(524, 7, MobCodes.Harvestable, "fiber");
        addItem(525, 8, MobCodes.Harvestable, "fiber");
        
        // Elite Roads <=> Tw_MOB_CRITTER_FIBER_ROADS_ELITE +25
        addItem(526, 4, MobCodes.Harvestable, "fiber");
        addItem(527, 5, MobCodes.Harvestable, "fiber");
        addItem(528, 6, MobCodes.Harvestable, "fiber");
        addItem(529, 7, MobCodes.Harvestable, "fiber");
        addItem(530, 8, MobCodes.Harvestable, "fiber");

        // Mists Green <=> Tx_MOB_CRITTER_FIBER_MISTS_GREEN +25
        addItem(549, 3, MobCodes.Harvestable, "fiber");
        addItem(550, 4, MobCodes.Harvestable, "fiber");
        addItem(551, 5, MobCodes.Harvestable, "fiber");
        addItem(552, 6, MobCodes.Harvestable, "fiber");
        addItem(553, 7, MobCodes.Harvestable, "fiber");
        addItem(554, 8, MobCodes.Harvestable, "fiber");
        
        // Mists Red <=> Tx_MOB_CRITTER_FIBER_MISTS_RED +25
        addItem(573, 3, MobCodes.Harvestable, "fiber");
        addItem(574, 4, MobCodes.Harvestable, "fiber");
        addItem(575, 5, MobCodes.Harvestable, "fiber");
        addItem(576, 6, MobCodes.Harvestable, "fiber");
        addItem(577, 7, MobCodes.Harvestable, "fiber");
        addItem(578, 8, MobCodes.Harvestable, "fiber");
        
        // Mists Dead <=> Tx_MOB_CRITTER_FIBER_MISTS_DEAD +25
        addItem(597, 3, MobCodes.Harvestable, "fiber");
        addItem(598, 4, MobCodes.Harvestable, "fiber");
        addItem(599, 5, MobCodes.Harvestable, "fiber");
        addItem(600, 6, MobCodes.Harvestable, "fiber");
        addItem(601, 7, MobCodes.Harvestable, "fiber");
        addItem(602, 8, MobCodes.Harvestable, "fiber");

        // ----------- Rock --------- //

        addItem(274, 3, MobCodes.Harvestable, "rock");
        addItem(275, 3, MobCodes.Harvestable, "rock");
        addItem(276, 5, MobCodes.Harvestable, "rock");
        addItem(277, 5, MobCodes.Harvestable, "rock");

        addItem(301, 3, MobCodes.Harvestable, "rock");
        addItem(308, 3, MobCodes.Harvestable, "rock");
        addItem(309, 4, MobCodes.Harvestable, "rock");
        addItem(310, 5, MobCodes.Harvestable, "rock");
        addItem(311, 6, MobCodes.Harvestable, "rock");
        addItem(312, 8, MobCodes.Harvestable, "rock");
        addItem(313, 4, MobCodes.Harvestable, "rock");
        addItem(314, 5, MobCodes.Harvestable, "rock");
        addItem(315, 5, MobCodes.Harvestable, "rock");
        addItem(316, 6, MobCodes.Harvestable, "rock");
        addItem(317, 8, MobCodes.Harvestable, "rock");

        addItem(359, 3, MobCodes.Harvestable, "rock");
        addItem(360, 3, MobCodes.Harvestable, "rock");
        addItem(361, 4, MobCodes.Harvestable, "rock");
        addItem(362, 5, MobCodes.Harvestable, "rock");
        addItem(363, 6, MobCodes.Harvestable, "rock");

        addItem(385, 5, MobCodes.Harvestable, "rock");
        addItem(386, 5, MobCodes.Harvestable, "rock");
        addItem(387, 6, MobCodes.Harvestable, "rock");
        addItem(388, 7, MobCodes.Harvestable, "rock");
        addItem(389, 8, MobCodes.Harvestable, "rock");

        addItem(401, 3, MobCodes.Harvestable, "rock");
        addItem(408, 3, MobCodes.Harvestable, "rock");
        addItem(409, 4, MobCodes.Harvestable, "rock");
        addItem(410, 5, MobCodes.Harvestable, "rock");
        addItem(411, 6, MobCodes.Harvestable, "rock");
        addItem(412, 7, MobCodes.Harvestable, "rock");
        addItem(413, 8, MobCodes.Harvestable, "rock");

        // Highland +25
        addItem(451, 3, MobCodes.Harvestable, "rock");
        addItem(452, 3, MobCodes.Harvestable, "rock");
        addItem(453, 5, MobCodes.Harvestable, "rock");
        addItem(454, 5, MobCodes.Harvestable, "rock");
        addItem(455, 7, MobCodes.Harvestable, "rock");

        // Roads <=> Tx_MOB_CRITTER_ROCK_ROADS +25
        addItem(486, 4, MobCodes.Harvestable, "rock");
        addItem(487, 5, MobCodes.Harvestable, "rock");
        addItem(488, 6, MobCodes.Harvestable, "rock");
        addItem(489, 7, MobCodes.Harvestable, "rock");
        addItem(490, 8, MobCodes.Harvestable, "rock");

        // Veteran Roads <=> Tx_MOB_CRITTER_ROCK_ROADS_VETERAN +25
        addItem(491, 4, MobCodes.Harvestable, "rock");
        addItem(492, 5, MobCodes.Harvestable, "rock");
        addItem(493, 6, MobCodes.Harvestable, "rock");
        addItem(494, 7, MobCodes.Harvestable, "rock");
        addItem(495, 8, MobCodes.Harvestable, "rock");

        // Elite Roads <=> Tx_MOB_CRITTER_ROCK_ROADS_ELITE +25
        addItem(496, 4, MobCodes.Harvestable, "rock");
        addItem(497, 5, MobCodes.Harvestable, "rock");
        addItem(498, 6, MobCodes.Harvestable, "rock");
        addItem(499, 7, MobCodes.Harvestable, "rock");
        addItem(500, 8, MobCodes.Harvestable, "rock");

        // Mists Green <=> Tx_MOB_CRITTER_ROCK_MISTS_GREEN +25
        addItem(537, 3, MobCodes.Harvestable, "rock");
        addItem(538, 4, MobCodes.Harvestable, "rock");
        addItem(539, 5, MobCodes.Harvestable, "rock");
        addItem(540, 6, MobCodes.Harvestable, "rock");
        addItem(541, 7, MobCodes.Harvestable, "rock");
        addItem(542, 8, MobCodes.Harvestable, "rock");

        // Mists Red <=> Tx_MOB_CRITTER_ROCK_MISTS_RED +25
        addItem(561, 3, MobCodes.Harvestable, "rock");
        addItem(562, 4, MobCodes.Harvestable, "rock");
        addItem(563, 5, MobCodes.Harvestable, "rock");
        addItem(564, 6, MobCodes.Harvestable, "rock");
        addItem(565, 7, MobCodes.Harvestable, "rock");
        addItem(566, 8, MobCodes.Harvestable, "rock");

        // Mists Dead <=> Tx_MOB_CRITTER_ROCK_MISTS_DEAD +25
        addItem(585, 3, MobCodes.Harvestable, "rock");
        addItem(586, 4, MobCodes.Harvestable, "rock");
        addItem(587, 5, MobCodes.Harvestable, "rock");
        addItem(588, 6, MobCodes.Harvestable, "rock");
        addItem(589, 7, MobCodes.Harvestable, "rock");
        addItem(590, 8, MobCodes.Harvestable, "rock");

        // ----------- Logs --------- //

        addItem(268, 3, MobCodes.Harvestable, "logs");
        addItem(272, 5, MobCodes.Harvestable, "logs");
        addItem(273, 7, MobCodes.Harvestable, "logs");

        addItem(269, 3, MobCodes.Harvestable, "logs");
        addItem(270, 3, MobCodes.Harvestable, "logs");
        addItem(271, 5, MobCodes.Harvestable, "logs");

        addItem(293, 3, MobCodes.Harvestable, "logs");
        addItem(294, 4, MobCodes.Harvestable, "logs");
        addItem(295, 5, MobCodes.Harvestable, "logs");
        addItem(296, 6, MobCodes.Harvestable, "logs");
        addItem(297, 8, MobCodes.Harvestable, "logs");

        addItem(298, 4, MobCodes.Harvestable, "logs");
        addItem(299, 5, MobCodes.Harvestable, "logs");
        addItem(300, 5, MobCodes.Harvestable, "logs");
        addItem(356, 5, MobCodes.Harvestable, "logs");
        addItem(301, 7, MobCodes.Harvestable, "logs");
        addItem(302, 8, MobCodes.Harvestable, "logs");

        addItem(353, 3, MobCodes.Harvestable, "logs");
        addItem(354, 3, MobCodes.Harvestable, "logs");
        addItem(355, 4, MobCodes.Harvestable, "logs");

        addItem(379, 5, MobCodes.Harvestable, "logs");
        addItem(380, 5, MobCodes.Harvestable, "logs");
        addItem(381, 6, MobCodes.Harvestable, "logs");
        addItem(382, 7, MobCodes.Harvestable, "logs");
        addItem(383, 8, MobCodes.Harvestable, "logs");

        addItem(402, 3, MobCodes.Harvestable, "logs");
        addItem(403, 4, MobCodes.Harvestable, "logs");
        addItem(404, 5, MobCodes.Harvestable, "logs");
        addItem(405, 6, MobCodes.Harvestable, "logs");
        addItem(406, 7, MobCodes.Harvestable, "logs");
        addItem(407, 8, MobCodes.Harvestable, "logs");

        // Forest +25
        addItem(446, 3, MobCodes.Harvestable, "logs");
        addItem(447, 3, MobCodes.Harvestable, "logs");
        addItem(448, 5, MobCodes.Harvestable, "logs");
        addItem(449, 5, MobCodes.Harvestable, "logs");
        addItem(450, 7, MobCodes.Harvestable, "logs");

        // Roads <=> Tx_MOB_CRITTER_WOOD_ROADS +25
        addItem(471, 4, MobCodes.Harvestable, "logs");
        addItem(472, 5, MobCodes.Harvestable, "logs");
        addItem(473, 6, MobCodes.Harvestable, "logs");
        addItem(474, 7, MobCodes.Harvestable, "logs");
        addItem(475, 8, MobCodes.Harvestable, "logs");

        // Roads Veteran <=> Tx_MOB_CRITTER_WOOD_ROADS_VETERAN +25
        addItem(476, 4, MobCodes.Harvestable, "logs");
        addItem(477, 5, MobCodes.Harvestable, "logs");
        addItem(478, 6, MobCodes.Harvestable, "logs");
        addItem(479, 7, MobCodes.Harvestable, "logs");
        addItem(480, 8, MobCodes.Harvestable, "logs");

        // Roads Elite <=> Tx_MOB_CRITTER_WOOD_ROADS_ELITE +25
        addItem(481, 4, MobCodes.Harvestable, "logs");
        addItem(482, 5, MobCodes.Harvestable, "logs");
        addItem(483, 6, MobCodes.Harvestable, "logs");
        addItem(484, 7, MobCodes.Harvestable, "logs");
        addItem(485, 8, MobCodes.Harvestable, "logs");

        // Mists Green <=> Tx_MOB_CRITTER_WOOD_MISTS_GREEN +25
        addItem(531, 3, MobCodes.Harvestable, "logs");
        addItem(532, 4, MobCodes.Harvestable, "logs");
        addItem(533, 5, MobCodes.Harvestable, "logs");
        addItem(534, 6, MobCodes.Harvestable, "logs");
        addItem(535, 7, MobCodes.Harvestable, "logs");
        addItem(536, 8, MobCodes.Harvestable, "logs");

        // Mists Red <=> Tx_MOB_CRITTER_WOOD_MISTS_RED +25
        addItem(555, 3, MobCodes.Harvestable, "logs");
        addItem(556, 4, MobCodes.Harvestable, "logs");
        addItem(557, 5, MobCodes.Harvestable, "logs");
        addItem(558, 6, MobCodes.Harvestable, "logs");
        addItem(559, 7, MobCodes.Harvestable, "logs");
        addItem(560, 8, MobCodes.Harvestable, "logs");

        // Mists Dead <=> Tx_MOB_CRITTER_WOOD_MISTS_DEAD +25
        addItem(579, 3, MobCodes.Harvestable, "logs");
        addItem(580, 4, MobCodes.Harvestable, "logs");
        addItem(581, 5, MobCodes.Harvestable, "logs");
        addItem(582, 6, MobCodes.Harvestable, "logs");
        addItem(583, 7, MobCodes.Harvestable, "logs");
        addItem(584, 8, MobCodes.Harvestable, "logs");

        addItem(639, 5, MobCodes.Harvestable, "logs");

        // ----------- Ore --------- //

        addItem(264, 3, MobCodes.Harvestable, "ore");
        addItem(265, 3, MobCodes.Harvestable, "ore");
        addItem(266, 5, MobCodes.Harvestable, "ore");
        addItem(267, 5, MobCodes.Harvestable, "ore");

        addItem(327, 7, MobCodes.Harvestable, "ore");
        addItem(328, 4, MobCodes.Harvestable, "ore");
        addItem(329, 5, MobCodes.Harvestable, "ore");
        addItem(330, 6, MobCodes.Harvestable, "ore");
        addItem(331, 7, MobCodes.Harvestable, "ore");
        addItem(332, 7, MobCodes.Harvestable, "ore");

        addItem(365, 3, MobCodes.Harvestable, "ore");
        addItem(366, 3, MobCodes.Harvestable, "ore");
        addItem(367, 4, MobCodes.Harvestable, "ore");
        addItem(368, 5, MobCodes.Harvestable, "ore");
        addItem(369, 6, MobCodes.Harvestable, "ore");

        addItem(391, 4, MobCodes.Harvestable, "ore");
        addItem(392, 5, MobCodes.Harvestable, "ore");
        addItem(393, 6, MobCodes.Harvestable, "ore");
        addItem(394, 7, MobCodes.Harvestable, "ore");
        addItem(395, 8, MobCodes.Harvestable, "ore");

        addItem(400, 7, MobCodes.Harvestable, "ore");
        addItem(415, 4, MobCodes.Harvestable, "ore");
        addItem(416, 5, MobCodes.Harvestable, "ore");
        addItem(417, 6, MobCodes.Harvestable, "ore");
        addItem(418, 7, MobCodes.Harvestable, "ore");
        addItem(419, 8, MobCodes.Harvestable, "ore");

        // Mountain +25
        addItem(441, 3, MobCodes.Harvestable, "ore");
        addItem(442, 4, MobCodes.Harvestable, "ore");
        addItem(443, 5, MobCodes.Harvestable, "ore");
        addItem(444, 5, MobCodes.Harvestable, "ore");
        addItem(445, 7, MobCodes.Harvestable, "ore");

        // Roads <=> Tx_MOB_CRITTER_ORE_ROADS +25
        addItem(501, 4, MobCodes.Harvestable, "ore");
        addItem(502, 5, MobCodes.Harvestable, "ore");
        addItem(503, 6, MobCodes.Harvestable, "ore");
        addItem(504, 7, MobCodes.Harvestable, "ore");
        addItem(505, 8, MobCodes.Harvestable, "ore");

        // Veteran Roads <=> Tx_MOB_CRITTER_ORE_ROADS_VETERAN +25
        addItem(506, 4, MobCodes.Harvestable, "ore");
        addItem(507, 5, MobCodes.Harvestable, "ore");
        addItem(508, 6, MobCodes.Harvestable, "ore");
        addItem(509, 7, MobCodes.Harvestable, "ore");
        addItem(510, 8, MobCodes.Harvestable, "ore");
        
        // Elite Roads <=> Tx_MOB_CRITTER_ORE_ROADS_ELITE +25
        addItem(511, 4, MobCodes.Harvestable, "ore");
        addItem(512, 5, MobCodes.Harvestable, "ore");
        addItem(513, 6, MobCodes.Harvestable, "ore");
        addItem(514, 7, MobCodes.Harvestable, "ore");
        addItem(515, 8, MobCodes.Harvestable, "ore");

        // Mists Green <=> Tx_MOB_CRITTER_ORE_MISTS_GREEN +25
        addItem(543, 3, MobCodes.Harvestable, "ore");
        addItem(544, 4, MobCodes.Harvestable, "ore");
        addItem(545, 5, MobCodes.Harvestable, "ore");
        addItem(546, 6, MobCodes.Harvestable, "ore");
        addItem(547, 7, MobCodes.Harvestable, "ore");
        addItem(548, 8, MobCodes.Harvestable, "ore");
        
        // Mists Red <=> Tx_MOB_CRITTER_ORE_MISTS_RED +25
        addItem(567, 3, MobCodes.Harvestable, "ore");
        addItem(568, 4, MobCodes.Harvestable, "ore");
        addItem(569, 5, MobCodes.Harvestable, "ore");
        addItem(570, 6, MobCodes.Harvestable, "ore");
        addItem(571, 7, MobCodes.Harvestable, "ore");
        addItem(572, 8, MobCodes.Harvestable, "ore");
        
        // Mists Dead <=> Tx_MOB_CRITTER_ORE_MISTS_DEAD +25
        addItem(591, 3, MobCodes.Harvestable, "ore");
        addItem(592, 4, MobCodes.Harvestable, "ore");
        addItem(593, 5, MobCodes.Harvestable, "ore");
        addItem(594, 6, MobCodes.Harvestable, "ore");
        addItem(595, 7, MobCodes.Harvestable, "ore");
        addItem(596, 8, MobCodes.Harvestable, "ore");

        // ----------- Boss --------- //

        // Spider : VEILWEAVER <=> Tx_MOB_MISTS_SPIDER +25
        addItem(299, 4, MobCodes.Boss, "veilweaver");
        addItem(300, 5, MobCodes.Boss, "veilweaver");
        addItem(301, 6, MobCodes.Boss, "veilweaver");
        addItem(302, 7, MobCodes.Boss, "veilweaver");
        addItem(303, 8, MobCodes.Boss, "veilweaver");

        // FAIRYDRAGON <=> Tx_MOB_MISTS_FAIRYDRAGON +25
        addItem(304, 4, MobCodes.Boss, "fairydragon");
        addItem(305, 5, MobCodes.Boss, "fairydragon");
        addItem(306, 6, MobCodes.Boss, "fairydragon");
        addItem(307, 7, MobCodes.Boss, "fairydragon");
        addItem(308, 8, MobCodes.Boss, "fairydragon");

        // GRIFFIN <=> Tx_MOB_MISTS_GRIFFIN +25
        addItem(309, 4, MobCodes.Boss, "griffin");
        addItem(310, 5, MobCodes.Boss, "griffin");
        addItem(311, 6, MobCodes.Boss, "griffin");
        addItem(312, 7, MobCodes.Boss, "griffin");
        addItem(313, 8, MobCodes.Boss, "griffin");

        addItem(738, 3, MobCodes.Boss, "rabble");
        addItem(740, 6, MobCodes.Boss, "rabble");
        addItem(765, 3, MobCodes.Boss, "keen");
        addItem(767, 6, MobCodes.Boss, "keen");
        addItem(792, 3, MobCodes.Boss, "pyromaniac");
        addItem(794, 6, MobCodes.Boss, "pyromaniac");

        addItem(820, 3, MobCodes.Boss, "enforcer");
        addItem(822, 6, MobCodes.Boss, "enforcer");

        addItem(969, 3, MobCodes.Boss, "lieutenant");
        addItem(970, 3, MobCodes.Boss, "lieutenant");
        addItem(971, 3, MobCodes.Boss, "lieutenant");
        addItem(974, 3, MobCodes.Boss, "lieutenant");
        addItem(975, 3, MobCodes.Boss, "lieutenant");
        addItem(977, 6, MobCodes.Boss, "commander");
        addItem(979, 3, MobCodes.Boss, "commander");

        addItem(1009, 3, MobCodes.Boss, "suppression");
        addItem(1010, 3, MobCodes.Boss, "suppression");
        addItem(1011, 3, MobCodes.Boss, "suppression");
        addItem(1017, 3, MobCodes.Boss, "masterof");
        addItem(1018, 3, MobCodes.Boss, "masterof");
        addItem(1019, 3, MobCodes.Boss, "masterof");

        addItem(1049, 3, MobCodes.Boss, "ritual");
        addItem(1051, 3, MobCodes.Boss, "ritual");
        addItem(1050, 3, MobCodes.Boss, "ritual");
        addItem(1057, 3, MobCodes.Boss, "mistress");
        addItem(1058, 3, MobCodes.Boss, "mistress");
        addItem(1059, 3, MobCodes.Boss, "mistress");
        addItem(1073, 3, MobCodes.Boss, "chosenofmorgana");
        addItem(1074, 3, MobCodes.Boss, "chosenofmorgana");
        addItem(1075, 3, MobCodes.Boss, "chosenofmorgana");

        addItem(1308, 7, MobCodes.Boss, "swordmaster");
        addItem(1335, 7, MobCodes.Boss, "marksman ");
        addItem(1336, 7, MobCodes.Boss, "nameless");
        addItem(1337, 8, MobCodes.Boss, "nameless");
        addItem(1371, 7, MobCodes.Boss, "cryomancer");
        addItem(1372, 8, MobCodes.Boss, "cryomancer");
        addItem(1389, 7, MobCodes.Boss, "lord");
        addItem(1390, 8, MobCodes.Boss, "lord");

        addItem(1407, 7, MobCodes.Boss, "mastercryomancer");
        addItem(1408, 8, MobCodes.Boss, "mastercryomancer");

        addItem(1708, 5, MobCodes.Boss, "warrior");
        addItem(1709, 5, MobCodes.Boss, "chief");
        addItem(1713, 5, MobCodes.Boss, "leader");
        addItem(1714, 5, MobCodes.Boss, "maiden");
        addItem(1716, 5, MobCodes.Boss, "graybeard");
        addItem(1717, 5, MobCodes.Boss, "grandfather");
        addItem(1720, 5, MobCodes.Boss, "elder");
        addItem(1721, 5, MobCodes.Boss, "druid");

        // ----------- Drone --------- //

        addItem(616, 5, MobCodes.Drone, "droneicon");
        addItem(617, 6, MobCodes.Drone, "droneicon");
        addItem(618, 7, MobCodes.Drone, "droneicon");
        addItem(619, 3, MobCodes.Drone, "droneicon");

        // ----------- Mist --------- //

        addItem(79, 5, MobCodes.MistPortal, "mist");
        addItem(80, 5, MobCodes.MistPortal, "mist");
        addItem(81, 5, MobCodes.MistPortal, "mist");
        addItem(82, 5, MobCodes.MistPortal, "mist");
        addItem(83, 5, MobCodes.MistPortal, "mist");
        addItem(84, 5, MobCodes.MistPortal, "mist");

        // ----------- Treasure --------- //

        addItem(685, 4, MobCodes.Events, "forssterling");
        addItem(686, 5, MobCodes.Events, "forssterling");
        addItem(691, 5, MobCodes.Events, "forssterling");

        addItem(692, 2, MobCodes.Events, "EVENTEASTERCHEST2");
        addItem(693, 3, MobCodes.Events, "EVENTEASTERCHEST2");
        addItem(694, 4, MobCodes.Events, "EVENTEASTERCHEST2");
        addItem(695, 5, MobCodes.Events, "EVENTEASTERCHEST2");
        addItem(696, 6, MobCodes.Events, "EVENTEASTERCHEST2");
        addItem(697, 7, MobCodes.Events, "EVENTEASTERCHEST2");
        addItem(698, 8, MobCodes.Events, "EVENTEASTERCHEST2");

        addItem(732, 2,MobCodes.Events, "EVENTEASTERCHEST1");
        addItem(733, 3,MobCodes.Events, "EVENTEASTERCHEST1");
        addItem(734, 4,MobCodes.Events, "EVENTEASTERCHEST1");
        addItem(735, 5,MobCodes.Events, "EVENTEASTERCHEST1");
        addItem(736, 6,MobCodes.Events, "EVENTEASTERCHEST1");
        addItem(737, 7,MobCodes.Events, "EVENTEASTERCHEST1");
        addItem(738, 8,MobCodes.Events, "EVENTEASTERCHEST1");


        // ----------- Guard --------- //

        addItem(2034, 5, MobCodes.Guard, "forssterling");
        addItem(2037, 5, MobCodes.Guard, "forssterling");
        addItem(2044, 5, MobCodes.Guard, "forssterling");
        addItem(2046, 4, MobCodes.Guard, "forssterling");
        addItem(2047, 5, MobCodes.Guard, "forssterling");
        addItem(2059, 4, MobCodes.Guard, "forssterling");

        // ----------- Mobs --------- //

        addItem(879, 3, MobCodes.Enemy, "thetford");
        addItem(880, 5, MobCodes.Enemy, "thetford");

        addItem(888, 3, MobCodes.Enemy, "thetford");
        addItem(889, 5, MobCodes.Enemy, "thetford");

        addItem(924, 3, MobCodes.Enemy, "thetford");
        addItem(925, 5, MobCodes.Enemy, "thetford");

        addItem(951, 3, MobCodes.Enemy, "thetford");
        addItem(952, 5, MobCodes.Enemy, "thetford");

        addItem(1121, 5, MobCodes.Enemy, "mist");
        addItem(1129, 5, MobCodes.Enemy, "mist");

        addItem(1133, 5, MobCodes.Enemy, "mist");
        addItem(1137, 5, MobCodes.Enemy, "mist");

        addItem(1161, 5, MobCodes.Enemy, "mist");
        addItem(1165, 5, MobCodes.Enemy, "mist");
        addItem(1169, 5, MobCodes.Enemy, "mist");

        addItem(1173, 5, MobCodes.Enemy, "mist");
        addItem(1177, 5, MobCodes.Enemy, "mist");
        addItem(1181, 5, MobCodes.Enemy, "mist");

        addItem(1201, 5, MobCodes.Enemy, "mist");
        addItem(1205, 5, MobCodes.Enemy, "mist");
        addItem(1209, 5, MobCodes.Enemy, "mist");
        addItem(1213, 5, MobCodes.Enemy, "mist");

        addItem(1437, 4, MobCodes.Enemy, "forssterling");
        addItem(1438, 5, MobCodes.Enemy, "mist");

        addItem(1442, 5, MobCodes.Enemy, "mist");

        addItem(1455, 4, MobCodes.Enemy, "forssterling");
        addItem(1456, 5, MobCodes.Enemy, "mist");
        addItem(1457, 5, MobCodes.Enemy, "forssterling");

        addItem(1460, 5, MobCodes.Enemy, "mist");

        addItem(1464, 4, MobCodes.Enemy, "forssterling");
        addItem(1465, 5, MobCodes.Enemy, "mist");
        addItem(1466, 5, MobCodes.Enemy, "forssterling");
        addItem(1469, 5, MobCodes.Enemy, "mist");

        addItem(1492, 5, MobCodes.Enemy, "mist");

        addItem(1496, 5, MobCodes.Enemy, "mist");

        addItem(1527, 4, MobCodes.Enemy, "forssterling");
        addItem(1528, 5, MobCodes.Enemy, "mist");
        addItem(1529, 5, MobCodes.Enemy, "forssterling");

        addItem(1882, 5, MobCodes.Enemy, "lymhurst");
        addItem(1883, 5, MobCodes.Enemy, "lymhurst");
        addItem(1887, 5, MobCodes.Enemy, "lymhurst");
        addItem(1888, 5, MobCodes.Enemy, "lymhurst");
        addItem(1889, 5, MobCodes.Enemy, "lieutenant");
        addItem(1892, 5, MobCodes.Enemy, "lieutenant");
        addItem(1884, 5, MobCodes.Enemy, "lieutenant");
        addItem(1895, 5, MobCodes.Enemy, "forssterling");

        addItem(1909, 5, MobCodes.Enemy, "forssterling");
        addItem(1913, 5, MobCodes.Enemy, "forssterling");
        addItem(1926, 5, MobCodes.Enemy, "forssterling");
        addItem(1929, 5, MobCodes.Enemy, "forssterling");
        addItem(1941, 5, MobCodes.Enemy, "forssterling");
        addItem(1967, 5, MobCodes.Enemy, "forssterling");
    }

    public void addItem(int typeId, int tier, MobCodes typeInt, String localization)
    {
        MobsInfo mobsInfo = new MobsInfo();
        mobsInfo.tier = tier;
        mobsInfo.type = typeInt;
        mobsInfo.localization = localization;
        mobinfo.put(typeId, mobsInfo);
    }

    public void AddMob(int id, int typeId, String name, float posX, float posY, int health, int enchant, int rarity)
    {
        SharedLocks.mobsHandlerLock.writeLock().lock();

        try
        {
            Mob h = new Mob(id, typeId, name, posX, posY, health, enchant, rarity);

            if (mobinfo.containsKey(typeId))
            {
                MobsInfo mobsInfo = mobinfo.get(typeId);

                assert mobsInfo != null;
                h.tier = mobsInfo.tier;
                h.type = mobsInfo.type;
                h.name = mobsInfo.localization;
                h.info = true;

                String JsonData = "typeId:" + typeId + " name:" + name + " health:" + health + " tier:"+ h.getTier() + " type:" + h.getType();
                Log.d("AddMob5",JsonData);
            }
            else
            {
                if(name != null && h.getHealth() == 1)
                {
                    h.tier = 5;
                    h.rarity = rarity;
                    h.name = "mist";
                    h.type = MobCodes.MistPortal;
                    h.info = true;
                }

                String JsonData = "typeId:" + typeId + " name:" + name + " health:" + health + " tier:"+ h.getTier() + " type:" + h.getType();
                Log.d("AddMob6",JsonData);
            }

            if (!mobsList.contains(h))
            {
                mobsList.add(h);
            }
        }
        finally
        {
            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }
    }

    public void RemoveMob(int id)
    {
        SharedLocks.mobsHandlerLock.writeLock().lock();

        try
        {
            mobsList.removeIf(x -> x.getId() == id);
        }
        finally
        {
            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }
    }

    public ArrayList<Mob> getMobList()
    {
        SharedLocks.mobsHandlerLock.readLock().lock();

        try
        {
            return  new ArrayList<>(mobsList);
        }
        finally
        {
            SharedLocks.mobsHandlerLock.readLock().unlock();
        }

    }

    public void UpdateMobPosition(int id, float posX, float posY)
    {
        SharedLocks.mobsHandlerLock.writeLock().lock();

        try
        {
            for (Mob mob : mobsList)
            {
                if (mob.getId() == id)
                {
                    mob.setPosX(posX);
                    mob.setPosY(posY);
                    break;
                }
            }
        }
        finally
        {
            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }
    }

    public void UpdateMobEnchantmentLevel(int mobId, int enchantmentLevel)
    {
        SharedLocks.mobsHandlerLock.writeLock().lock();

        try
        {
            for (Mob mob : mobsList)
            {
                if (mob.getId() == mobId)
                {
                    mob.setEnchantmentLevel(enchantmentLevel);
                    break;
                }
            }
        }
        finally
        {
            SharedLocks.mobsHandlerLock.writeLock().unlock();
        }
    }
}