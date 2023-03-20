package `is`.hi.hbv601g.projectplanner.data

import java.time.LocalDate

fun loadProjects(): List<Project> {
    return listOf<Project>(
        Project(1,1,"Ameno", "Undirbúa það sem á að sýna á brakandi ferskum fimmtudagsfundi!"),
        Project(2,1,"Omenare", "Verkefnislýsing :)"),
        Project(3,1,"Imperavi", "Verkefnislýsing :)"),
        Project(4,1,"Ameno", "Verkefnislýsing :)"),
        Project(5,1,"Dimere", "Verkefnislýsing :)"),
        Project(6,1,"Dimere", "Verkefnislýsing :)"),
        Project(7,1,"Matiro", "Verkefnislýsing :)"),
        Project(8,1,"Ameno", "Verkefnislýsing :)"),
        Project(9,1,"Dori me", "Verkefnislýsing :)"),
        Project(10,2,"Interimo", "Verkefnislýsing :)"),
        Project(11,2,"adapare", "Verkefnislýsing :)"),
        Project(12,2,"Dori me", "Verkefnislýsing :)"),
        Project(13,2,"Ameno", "Verkefnislýsing :)"),
        Project(14,2,"Ameno", "Verkefnislýsing :)"),
        Project(15,2,"Latire", "Verkefnislýsing :)")
    )
}

fun loadTasks(): List<Task> {
    return listOf<Task> (
        Task(1,1,"Layouts", "Fix the layouts", "19.03.2023",2),
        Task(2,1,"Sideswiping","Add the sideswiping", "19.03.2023",2),
        //Task(3,1,"Task"),
        //Task(4,1,"Ameno"),
        //Task(5,1,"Omenare"),
        //Task(6,2,"Ameno")
    )
}



fun loadGroupMembers(): List<GroupMembers> {
    return listOf<GroupMembers> (
        GroupMembers(1,"Óskar the Magnificent","osa16@hi.is", 1),
        GroupMembers(2,"Ísak CringeLORD", "isl5@hi.is", 1),
        GroupMembers(3,"Vigdís Bossman", "vigdis@hi.is", 1),
    )
}