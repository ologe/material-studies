package dev.olog.fortnightly.drawer

enum class DrawerCategory {
    FrontPage,
    World,
    US,
    Politics,
    Business,
    Tech,
    Science,
    Sports,
    Travel,
    Culture;

    override fun toString(): String = when (this) {
        FrontPage -> "Front Page"
        else -> super.toString()
    }

}

enum class DrawerSubCategory {
    Vacations,
    AirTravel,
    WaterTravel,
    RailTravel,
    Hotels,
    Spas;

    override fun toString() = when (this) {
        AirTravel -> "Air Travel"
        WaterTravel -> "Water Travel"
        RailTravel -> "Rail Travel"
        else -> super.toString()
    }

}