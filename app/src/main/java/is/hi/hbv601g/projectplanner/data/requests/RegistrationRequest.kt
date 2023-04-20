package `is`.hi.hbv601g.projectplanner.data.requests

data class RegistrationRequest(
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val phoneNumber: Int
)
