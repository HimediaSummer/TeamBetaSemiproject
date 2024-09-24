package beta.function.game.dto;

public class GameDTO {

    private int gameCode;
    private String gameName;
    private String gameStorage;
    private int gamePrice;
    private String uploadDate;
    private String gameOrigin;
    private String gameRequirement;
    private String gamePicture;
    private String gameThumbnail;

    public GameDTO() {
    }

    public GameDTO(int gameCode, String gameName, String gameStorage, int gamePrice, String uploadDate, String gameOrigin, String gameRequirement, String gamePicture, String gameThumbnail) {
        this.gameCode = gameCode;
        this.gameName = gameName;
        this.gameStorage = gameStorage;
        this.gamePrice = gamePrice;
        this.uploadDate = uploadDate;
        this.gameOrigin = gameOrigin;
        this.gameRequirement = gameRequirement;
        this.gamePicture = gamePicture;
        this.gameThumbnail = gameThumbnail;
    }

    public int getGameCode() {
        return gameCode;
    }

    public void setGameCode(int gameCode) {
        this.gameCode = gameCode;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameStorage() {
        return gameStorage;
    }

    public void setGameStorage(String gameStorage) {
        this.gameStorage = gameStorage;
    }

    public int getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(int gamePrice) {
        this.gamePrice = gamePrice;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getGameOrigin() {
        return gameOrigin;
    }

    public void setGameOrigin(String gameOrigin) {
        this.gameOrigin = gameOrigin;
    }

    public String getGameRequirement() {
        return gameRequirement;
    }

    public void setGameRequirement(String gameRequirement) {
        this.gameRequirement = gameRequirement;
    }

    public String getGamePicture() {
        return gamePicture;
    }

    public void setGamePicture(String gamePicture) {
        this.gamePicture = gamePicture;
    }

    public String getGameThumbnail() {
        return gameThumbnail;
    }

    public void setGameThumbnail(String gameThumbnail) {
        this.gameThumbnail = gameThumbnail;
    }

    @Override
    public String toString() {
        return "gameDTO{" +
                "gameCode=" + gameCode +
                ", gameName='" + gameName + '\'' +
                ", gameStorage='" + gameStorage + '\'' +
                ", gamePrice=" + gamePrice +
                ", uploadDate='" + uploadDate + '\'' +
                ", gameOrigin='" + gameOrigin + '\'' +
                ", gameRequirement='" + gameRequirement + '\'' +
                ", gamePicture='" + gamePicture + '\'' +
                ", gameThumbnail='" + gameThumbnail + '\'' +
                '}';
    }
}
