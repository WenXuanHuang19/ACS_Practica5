package com.selenium.practice.tests;

import com.selenium.practice.drivers.DriverManager;
import com.selenium.practice.pages.LoginPage;
import com.selenium.practice.pages.DashboardPage;
import com.selenium.practice.pages.AdminPage;
import com.selenium.practice.pages.PIMPage;
import com.selenium.practice.pages.RecruitmentPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;

public class OrangeHRMTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;
    private PIMPage pimPage;
    private RecruitmentPage recruitmentPage;

    @Before
    public void setUp() {
        driver = DriverManager.initDriver("chrome");
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        adminPage = new AdminPage(driver);
        pimPage = new PIMPage(driver);
        loginPage.navigateToLogin();
        recruitmentPage = new RecruitmentPage(driver);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }


    // CASOS POSITIVOS

    @Test
    public void testSuccessfulLogin() {
        System.out.println("Test: Login exitoso");

        loginPage.login("Admin", "admin123");

        assertTrue("Dashboard should be displayed",
                dashboardPage.isDashboardDisplayed());
        assertTrue("URL should contain dashboard",
                dashboardPage.getCurrentUrl().contains("dashboard"));
        assertEquals("Dashboard title should match",
                "Dashboard",
                dashboardPage.getDashboardTitle());

        System.out.println("Test completado exitosamente");
    }

    @Test
    public void testDashboardMenusVisible() {
        System.out.println("Test: Menus visibles en dashboard");

        loginPage.login("Admin", "admin123");

        assertTrue("Dashboard should be displayed",
                dashboardPage.isDashboardDisplayed());
        assertTrue("Admin menu should be visible",
                dashboardPage.isAdminMenuVisible());
        assertTrue("PIM menu should be visible",
                dashboardPage.isPIMMenuVisible());
        assertTrue("Recruitment menu should be visible",
                dashboardPage.isRecruitmentMenuVisible());

        System.out.println("Todos los menus visibles");
    }


    // CASOS NEGATIVOS

    @Test
    public void testLoginWithInvalidPassword() {
        System.out.println("Test: Login con password invalido");

        loginPage.login("Admin", "wrongpassword");

        assertTrue("Error message should be displayed",
                loginPage.isErrorMessageDisplayed());
        assertTrue("Should remain on login page",
                loginPage.isLoginPageDisplayed());
        assertEquals("Error message should match",
                "Invalid credentials",
                loginPage.getErrorMessage());

        System.out.println("Error detectado correctamente");
    }

    @Test
    public void testLoginWithInvalidUsername() {
        System.out.println("Test: Login con username invalido");

        loginPage.login("InvalidUser", "admin123");

        assertTrue("Error message should be displayed",
                loginPage.isErrorMessageDisplayed());
        assertTrue("Should remain on login page",
                loginPage.isLoginPageDisplayed());

        System.out.println("Error detectado correctamente");
    }

    @Test
    public void testLoginWithEmptyFields() {
        System.out.println("Test: Login con campos vacios");

        loginPage.clickLoginButton();

        assertTrue("Should remain on login page",
                loginPage.isLoginPageDisplayed());
        assertTrue("URL should still contain login",
                loginPage.getCurrentUrl().contains("login"));

        System.out.println("Validacion de campos vacios correcta");
    }


    // NAVEGACION

    @Test
    public void testNavigateToAdmin() {
        System.out.println("Test: Navegacion a Admin");

        loginPage.login("Admin", "admin123");
        dashboardPage.clickAdminMenu();

        assertTrue("Admin page should be displayed",
                adminPage.isAdminPageDisplayed());
        assertTrue("URL should contain admin",
                adminPage.getCurrentUrl().contains("admin"));
        assertEquals("Page title should be Admin",
                "Admin",
                adminPage.getAdminPageTitle());

        System.out.println("Navegacion exitosa a Admin");
    }

    @Test
    public void testNavigateToPIM() {
        System.out.println("Test: Navegacion a PIM");

        loginPage.login("Admin", "admin123");
        dashboardPage.clickPIMMenu();

        assertTrue("PIM page should be displayed",
                pimPage.isPIMPageDisplayed());
        assertTrue("URL should contain pim",
                pimPage.getCurrentUrl().contains("pim"));
        assertEquals("Page title should be PIM",
                "PIM",
                pimPage.getPIMPageTitle());

        System.out.println("Navegacion exitosa a PIM");
    }

    @Test
    public void testNavigateToRecruitment() {
        System.out.println("Test: Navegacion a Recruitment");

        loginPage.login("Admin", "admin123");
        dashboardPage.clickRecruitmentMenu();

        // Validar URL contiene recruitment
        assertTrue("URL should contain recruitment",
                driver.getCurrentUrl().contains("recruitment"));

        System.out.println("Navegacion exitosa a Recruitment");
    }


    // INTERACCION

    @Test
    public void testUserDropdownMenu() {
        System.out.println("Test: User dropdown menu");

        loginPage.login("Admin", "admin123");

        String userNameBefore = dashboardPage.getUserName();
        assertFalse("Username should be visible", userNameBefore.isEmpty());

        dashboardPage.clickUserDropdown();

        System.out.println("Dropdown abierto correctamente");
    }

    @Test
    public void testLogout() {
        System.out.println("Test: Logout");

        loginPage.login("Admin", "admin123");
        assertTrue("Should be on dashboard",
                dashboardPage.isDashboardDisplayed());

        System.out.println("Haciendo click en dropdown...");
        dashboardPage.clickUserDropdown();

        // Esperar un poco para ver el menu
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Haciendo click en logout...");
        dashboardPage.clickLogout();

        System.out.println("URL actual: " + loginPage.getCurrentUrl());
        System.out.println("Login page displayed: " + loginPage.isLoginPageDisplayed());

        assertTrue("Should return to login page",
                loginPage.isLoginPageDisplayed());
        assertTrue("URL should contain login",
                loginPage.getCurrentUrl().contains("login"));

        System.out.println("Logout exitoso");
    }

    // TC-012: Validar titulo de pagina Login
    @Test
    public void testLoginPageTitle() {
        System.out.println("Test: Titulo de pagina Login");

        String title = loginPage.getPageTitle();

        assertTrue("Page title should contain OrangeHRM",
                title.contains("OrangeHRM"));

        System.out.println("Titulo: " + title);
    }

    // TC-013: Validar boton Forgot Password visible
    @Test
    public void testForgotPasswordVisible() {
        System.out.println("Test: Forgot Password visible");

        assertTrue("Forgot password link should be visible",
                loginPage.isForgotPasswordVisible());

        System.out.println("Link visible correctamente");
    }

    // TC-014: Validar URL de pagina Admin
    @Test
    public void testAdminPageURL() {
        System.out.println("Test: URL de pagina Admin");

        loginPage.login("Admin", "admin123");
        dashboardPage.clickAdminMenu();

        String url = adminPage.getCurrentUrl();

        assertTrue("URL should contain admin", url.contains("admin"));
        assertTrue("URL should contain viewSystemUsers",
                url.contains("viewSystemUsers"));

        System.out.println("URL: " + url);
    }

    // TC-015: Interaccion con dropdown User Role (OBLIGATORIO)
    @Test
    public void testUserRoleDropdownInteraction() {
        System.out.println("Test: Interaccion con dropdown User Role");

        loginPage.login("Admin", "admin123");
        dashboardPage.clickAdminMenu();

        assertTrue("Admin page should be displayed",
                adminPage.isAdminPageDisplayed());

        // Interactuar con dropdown
        System.out.println("Seleccionando opcion Admin en dropdown...");
        adminPage.selectUserRole("Admin");

        // Hacer click en Search
        System.out.println("Haciendo click en Search...");
        adminPage.clickSearchButton();

        // Validar que seguimos en Admin
        assertTrue("Should remain on admin page",
                adminPage.getCurrentUrl().contains("admin"));

        System.out.println("Dropdown y busqueda completados");
    }

    // TC-16: Validar subtitulo Employee Information en PIM
    @Test
    public void testPIMEmployeeInfoDisplayed() {
        System.out.println("Test: Validar Employee Information en PIM");

        loginPage.login("Admin", "admin123");
        dashboardPage.clickPIMMenu();

        assertTrue("PIM page should be displayed",
                pimPage.isPIMPageDisplayed());

        String subtitle = pimPage.getEmployeeInfoSubtitle();
        assertFalse("Employee Information should not be empty", subtitle.isEmpty());
        assertEquals("Subtitle should be Employee Information",
                "Employee Information",
                subtitle);

        System.out.println("Employee Information visible correctamente");
    }


    // TC-17: Validar subtitulo Candidates en Recruitment
    @Test
    public void testRecruitmentCandidatesDisplayed() {
        System.out.println("Test: Validar Candidates en Recruitment");

        loginPage.login("Admin", "admin123");
        dashboardPage.clickRecruitmentMenu();

        assertTrue("Recruitment page should be displayed",
                recruitmentPage.isRecruitmentPageDisplayed());
        assertTrue("Candidates subtitle should be displayed",
                recruitmentPage.isCandidatesSubtitleDisplayed());
        assertEquals("Subtitle should be Candidates",
                "Candidates",
                recruitmentPage.getCandidatesSubtitle());

        System.out.println("Candidates visible correctamente");
    }

}