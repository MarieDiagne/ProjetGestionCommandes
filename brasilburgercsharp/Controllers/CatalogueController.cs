private readonly IMenuService _menuService;

public CatalogueController(IBurgerService burgerService, IMenuService menuService)
{
    _burgerService = burgerService;
    _menuService = menuService;
}

public async Task<IActionResult> Menus()
{
    var menus = await _menuService.GetAll();
    return View(menus);
}

public async Task<IActionResult> DetailsMenu(int id)
{
    var menu = await _menuService.GetById(id);
    if (menu == null) return NotFound();
    return View(menu);
}
