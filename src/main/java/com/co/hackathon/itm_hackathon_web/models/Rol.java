package com.co.hackathon.itm_hackathon_web.models;

public enum Rol {
    ADMIN, USER, GUEST
}

// ❌ practica if (miembro.getRol().equals("admin")) { ... }
// ✅ practica if (miembro.getRol() == Rol.ADMIN) { ... }
