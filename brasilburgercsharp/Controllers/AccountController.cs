using Microsoft.AspNetCore.Mvc;
using BrasilBurger.Services.Interfaces;
using BrasilBurger.Models.Entities;
using BrasilBurger.Helpers;

namespace BrasilBurger.Controllers
{
    public class AccountController : Controller
    {
        private readonly IAuthService _authService;

        public AccountController(IAuthService authService)
        {
            _authService = authService;
        }

        public IActionResult Login() => View();

        [HttpPost]
        public async Task<IActionResult> Login(string email, string password)
        {
            var client = await _authService.Login(email, password);
            if (client == null)
            {
                ViewBag.Error = "Identifiants invalides";
                return View();
            }

            HttpContext.Session.Set("client", client);
            return RedirectToAction("Index", "Catalogue");
        }

        public IActionResult Register() => View();

        [HttpPost]
        public async Task<IActionResult> Register(Client client)
        {
            await _authService.Register(client);
            return RedirectToAction("Login");
        }

        public IActionResult Logout()
        {
            HttpContext.Session.Clear();
            return RedirectToAction("Login");
        }
    }
}
