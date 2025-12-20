using Microsoft.EntityFrameworkCore;
using brasilburgercsharp.Data;
using brasilburgercsharp.Repositories.Interfaces;
using brasilburgercsharp.Repositories.Implementations;
using brasilburgercsharp.Services.Interfaces;
using brasilburgercsharp.Services.Implementations;
using Microsoft.AspNetCore.Authentication.Cookies;

var builder = WebApplication.CreateBuilder(args);

// Correction de la configuration DB
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseNpgsql(builder.Configuration.GetConnectionString("DefaultConnection")));

builder.Services.AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)
    .AddCookie(options => {
        options.LoginPath = "/Account/Login";
    });

builder.Services.AddHttpContextAccessor();
builder.Services.AddDistributedMemoryCache();
builder.Services.AddSession(options => {
    options.IdleTimeout = TimeSpan.FromMinutes(30);
});

// Enregistrement des services
builder.Services.AddScoped<IProduitRepository, ProduitRepository>();
builder.Services.AddScoped<ICommandeRepository, CommandeRepository>();
builder.Services.AddScoped<IProduitService, ProduitService>();
builder.Services.AddScoped<ICommandeService, CommandeService>();

builder.Services.AddScoped<IPaiementService, PaiementService>();
builder.Services.AddScoped<IClientService, ClientService>();

builder.Services.AddControllersWithViews();
// Ajoutez ces lignes avec les autres AddScoped
builder.Services.AddScoped<IZoneService, ZoneService>();
builder.Services.AddScoped<IPanierService, PanierService>();
builder.Services.AddScoped<IZoneRepository, ZoneRepository>(); // Si vous avez un repo pour les zones

var app = builder.Build();

if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();
app.UseSession();
app.UseAuthentication();
app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Catalogue}/{action=Index}/{id?}");

app.Run();