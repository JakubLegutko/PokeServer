import PokeClient from "@przemyslaw-grobecki/api/dist/generated/PokeClient";
import LeagueApi from "@przemyslaw-grobecki/api/dist/generated/apis/LeagueApi";
import { League } from "@przemyslaw-grobecki/api/dist/generated/resources/League";

jest.setTimeout(60000);

describe("CRUD", () => {
    //TODO: There should be seperate test suite (describe block) for each user type
    const admin = {
        email: "test@test.test",
        password: "Test1234!@#$",
        firstName: "test",
        lastName: "test",
        role: "ADMIN"
    }

    const pokeClient : PokeClient = new PokeClient("localhost", "8090");
    
    beforeAll(async () => {
        //TODO: Create fixtures for database cleaning and setup
        //For now uncomment once before running the tests
    //    await pokeClient.Register(admin.email, admin.password, admin.firstName, admin.lastName, admin.role);
    });
    

    it("Should get all leagues", async () => {
        //Arrange
        const token = await pokeClient.Login(admin.email,admin.password);

        //Act
        const leagueApi : LeagueApi = pokeClient.getLeagueApi(token);
        const result : League[] = await leagueApi.GetAll();
    });

    it("Should get league by id", async () => {
        //Arrange
        const token = await pokeClient.Login(admin.email,admin.password);

        //Act
        const leagueApi : LeagueApi = pokeClient.getLeagueApi(token);
        const result : League = await leagueApi.Get("0");
    });

    it("Should create league", async () => {
        //Arrange
        const token = await pokeClient.Login(admin.email,admin.password);

        //Act
        const leagueApi : LeagueApi = pokeClient.getLeagueApi(token);
        const newLeague : League = {
            id: Math.random(),
            imageUrl: "",
            name: ""
        }
        await leagueApi.Post(newLeague);
    });

    it("Should update league", async () => {
        //Arrange
        const token = await pokeClient.Login(admin.email,admin.password);

        //Act
        const leagueApi : LeagueApi = pokeClient.getLeagueApi(token);
        const newLeague : League = {
            id: Math.random(),
            imageUrl: "",
            name: ""
        }
        await leagueApi.Patch("0", newLeague);
    });

    it("Should delete league", async () => {
        //Arrange
        const token = await pokeClient.Login(admin.email,admin.password);

        //Act
        const leagueApi : LeagueApi = pokeClient.getLeagueApi(token);
        await leagueApi.Delete("0");
    });
})