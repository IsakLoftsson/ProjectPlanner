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
        Task(1,1,"Layouts", "Fix the layouts", "21.03.2023",2, "In Progress"),
        Task(2,1,"Sideswiping","Add the sideswiping", "19.03.2023",1, "Completed"),
        Task(3,1,"Task","Fix task information", "22.03.2023",2, "In Progress"),
        Task(4,1,"Status","Add status to Tasks", "19.03.2023",2, "Completed"),
        Task(5,1,"Edit Status","Add editability to statuses ", "19.03.2023",2, "In Progress"),
        Task(6,2,"Edit Deadline","Add editability to deadline", "19.03.2023",2, "In Progress")
    )
}



fun loadGroupMembers(): List<GroupMembers> {
    return listOf<GroupMembers> (
        GroupMembers(1,"Óskar the Magnificent","osa16@hi.is", 1),
        GroupMembers(2,"Ísak CringeLORD", "isl5@hi.is", 1),
        GroupMembers(3,"Óskar the Great", "oskar@hi.is", 1),
        GroupMembers(4,"Ísak alt. account", "isak@hi.is", 2)
    )
}

fun loadUsers(): List<Users> {
    return listOf<Users> (
        Users(1,"Óskar the Fabulous","osa16@hi.is"),
        Users(2,"THE Ísak", "isl5@hi.is"),
        Users(3,"Óskar the Great", "oskar@hi.is"),
        Users(4,"Ísak alt. account", "isak@hi.is"),
        Users(5,"Óskar the Fabulous","oskar16@hi.is"),
        Users(6,"THE Ísak", "isak5@hi.is")
    )
}

fun loadComments(): List<Comments> {
    return listOf<Comments> (
        Comments(1,"We started the comments.", 1),
        Comments(2,"Currently not working, on a coffee break.", 1),
        Comments(3,"Back to work.", 1),
        Comments(4,"Almost done.", 1),
        Comments(5,"I was wrong, not almost done.", 1),
        Comments(6,"Almost done for real now.", 1),
        Comments(6,"Almost done.", 2),
        Comments(6,"Almost done.", 3)
    )
}