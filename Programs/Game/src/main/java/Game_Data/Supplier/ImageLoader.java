package Game_Data.Supplier;

import Level.Data.Config.PlayerConfig;
import javafx.scene.image.Image;

public class ImageLoader {

    // Skill Icons
    private static final String ATTACK_ICON_Path    = "/Picture/Skill_Icon/Cmp_Attack.png";
    private static final String DEFEND_ICON_Path    = "/Picture/Skill_Icon/Cmp_Defend.png";
    private static final String DASH_ICON_Path      = "/Picture/Skill_Icon/Dash.png";
    private static final String CMP_DASH_ICON_Path  = "/Picture/Skill_Icon/Cmp_Dash.png";
    private static final String EMPTY_ICON_Path     = "/Picture/Skill_Icon/Empty_Skill.png";

    // Map components
    private static final String FOREST_Path     = "/Picture/Map Components/Forest.png";
    private static final String GRAVEYARD_Path  = "/Picture/Map Components/Graveyard.png";
    private static final String BRIDGE_Path     = "/Picture/Map Components/Bridge.png";
    private static final String FISH_PORT_Path  = "/Picture/Map Components/Fish_Port.png";
    private static final String LAKE_Path       = "/Picture/Map Components/Lake.png";
    private static final String MOUNTAIN_Path   = "/Picture/Map Components/Mountain.png";
    private static final String TOWER_Path      = "/Picture/Map Components/Tower.png";
    private static final String DRAGON_Path     = "/Picture/Map Components/Dragon.png";
    private static final String MAP_PORTAL_Path = "/Picture/Map Components/Portal.png";
    private static final String GAME_BACKGROUND_Path = "/Picture/Map Components/Map_Background.png";

    // GameScene Icon
    private static final String COIN_Path       = "/Picture/Game_Icon/Coin_Image.png";
    private static final String CHARACTER_PATH  = "/Picture/Game_Icon/Character.png";
    private static final String STORE_Path      = "/Picture/Game_Icon/Store.png";
    private static final String SETTING_PATH    = "/Picture/Game_Icon/Setting.png";
    private static final String ENCYCLOPEDIA_PATH = "/Picture/Game_Icon/Encyclopedia.png";

    // StartScene
    private static final String STARTSCENE_IMAGE_Path = "/Picture/StartScene/Background.png";

    // Level Images
    private static final String COIN_ANIMATION_Path = "/Picture/Level_Picture/Coin_Animation.gif";
    private static final String STAKE_Path          = "/Picture/Level_Picture/Character/Stake.png";
    private static final String PLAYER_LEFT_Path    = "/Picture/Level_Picture/Character/Character_Left.png";
    private static final String PLAYER_RIGHT_Path   = "/Picture/Level_Picture/Character/Character_Right.png";
    private static final String PORTAL_Path         = "/Picture/Level_Picture/Character/Portal.png";
    private static final String LEVEL01_MAP_Path    = "/Picture/Level_Picture/map.png";
    private static final String HEART_FULL_Path     = "/Picture/Level_Picture/HeartFull.png";
    private static final String HEART_EMPTY_Path    = "/Picture/Level_Picture/HeartEmpty.png";



    // Getter Method

    public static final Image ATTACK_ICON = new Image(SkillSupplier.class.getResourceAsStream(ATTACK_ICON_Path));
    public static final Image DEFEND_ICON = new Image(SkillSupplier.class.getResourceAsStream(DEFEND_ICON_Path));
    public static final Image DASH_ICON = new Image(SkillSupplier.class.getResourceAsStream(DASH_ICON_Path));
    public static final Image CMP_DASH_ICON = new Image(SkillSupplier.class.getResourceAsStream(CMP_DASH_ICON_Path));
    public static final Image EMPTY_ICON = new Image(SkillSupplier.class.getResourceAsStream(EMPTY_ICON_Path));
    public static final Image[] SKILL_ICONS_UI = new Image[]{
            EMPTY_ICON, ATTACK_ICON, DEFEND_ICON, DASH_ICON
    };
    public static final Image[] SKILL_ICONS_LEVEL = new Image[]{
            EMPTY_ICON, ATTACK_ICON, DEFEND_ICON, CMP_DASH_ICON
    };


    public static final Image FOREST = new Image(SkillSupplier.class.getResourceAsStream(FOREST_Path));
    public static final Image GRAVEYARD = new Image(SkillSupplier.class.getResourceAsStream(GRAVEYARD_Path));
    public static final Image BRIDGE = new Image(SkillSupplier.class.getResourceAsStream(BRIDGE_Path));
    public static final Image FISH_PORT = new Image(SkillSupplier.class.getResourceAsStream(FISH_PORT_Path));
    public static final Image LAKE = new Image(SkillSupplier.class.getResourceAsStream(LAKE_Path));
    public static final Image MOUNTAIN = new Image(SkillSupplier.class.getResourceAsStream(MOUNTAIN_Path));
    public static final Image TOWER = new Image(SkillSupplier.class.getResourceAsStream(TOWER_Path));
    public static final Image DRAGON = new Image(SkillSupplier.class.getResourceAsStream(DRAGON_Path));
    public static final Image MAP_PORTAL = new Image(SkillSupplier.class.getResourceAsStream(MAP_PORTAL_Path));
    public static final Image GAME_BACKGROUND = new Image(SkillSupplier.class.getResourceAsStream(GAME_BACKGROUND_Path));


    public static final Image COIN = new Image(ImageLoader.class.getResourceAsStream(COIN_Path));
    public static final Image CHARACTER_ICON = new Image(ImageLoader.class.getResourceAsStream(CHARACTER_PATH));
    public static final Image STORE_ICON = new Image(ImageLoader.class.getResourceAsStream(STORE_Path));
    public static final Image ENCYCLOPEDIA_ICON = new Image(ImageLoader.class.getResourceAsStream(ENCYCLOPEDIA_PATH));
    public static final Image SETTING_ICON = new Image(ImageLoader.class.getResourceAsStream(SETTING_PATH));


    public static final Image STAKE = new Image(ImageLoader.class.getResourceAsStream(STAKE_Path));
    public static final Image PLAYER_LEFT = new Image(PlayerConfig.class.getResourceAsStream(PLAYER_LEFT_Path));
    public static final Image PLAYER_RIGHT =  new Image(PlayerConfig.class.getResourceAsStream(PLAYER_RIGHT_Path));
    public static final Image PORTAL = new Image(PlayerConfig.class.getResourceAsStream(PORTAL_Path));


    public static final Image LEVEL01_MAP = new Image(ImageLoader.class.getResourceAsStream(LEVEL01_MAP_Path));
    public static final Image HEART_FULL = new Image(ImageLoader.class.getResourceAsStream(HEART_FULL_Path));
    public static final Image HEART_EMPTY = new Image(ImageLoader.class.getResourceAsStream(HEART_EMPTY_Path));
    public static final Image COIN_ANIMATION = new Image(ImageLoader.class.getResourceAsStream(COIN_ANIMATION_Path));

    public static final Image STARTSCENE_BACKGROUND = new Image(ImageLoader.class.getResourceAsStream(STARTSCENE_IMAGE_Path));
}
