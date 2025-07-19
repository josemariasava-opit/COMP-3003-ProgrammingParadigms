(*## Question 1: Functional Programming Principles

Create an F# application that processes a list of student records, where each record contains:

- Name
- Age
- Grade

Your program should:

**1.** Filter students by specific criteria, such as:

- Age greater than 18
- Grade above a given threshold

**2.** Sort the list (e.g., by grade descending or name alphabetically)

**3.** Use map to transform student data (e.g., apply a grade curve or format output strings) 

Sava Josè Maria 
*)

// record type student 
type Student = {
    Name  : string // name of the student 
    Age   : int // age of the student 
    Grade : float // grade range 50-100 
}

// List of student records
let students = [
    { Name = "Luca";Age = 20; Grade = 78.5 }
    { Name = "Maria";Age = 17; Grade = 85.0 }
    { Name = "Carla";Age = 19; Grade = 65.0 }
    { Name = "Giovanni";Age = 22; Grade = 92.0 }
    { Name = "Damiano";Age = 18; Grade = 70.0 }
    { Name = "Andrea";Age = 16; Grade = 92.0 }
    { Name = "Josè";Age = 21; Grade = 55.0 }
]

// Filter students who are older than 18
let older = 
    students 
    |> List.filter (fun s -> s.Age > 18)

// Filter students whose grade is above a given threshold
let goodGrade threshold = 
    students 
    |> List.filter (fun s -> s.Grade > threshold)

// Sort students by name (alphabetically)
let sortedByName = 
    students 
    |> List.sortBy (fun s -> s.Name)

// Sort students by grade descending
let sortedByGradeDescending = 
    students 
    |> List.sortBy (fun s -> -s.Grade) // Negative to sort descending


// Apply a grade curve, add 5 bonus points to each student 
let curvedGrades curveAmount = 
    students
    |> List.map (fun s -> 
        { s with Grade = s.Grade + curveAmount })

// Format student record into strings for display
let formatStudentInfo = 
    students
    |> List.map (fun s ->
        sprintf "Name: %s, Age: %d, Grade: %.1f" s.Name s.Age s.Grade)

// Function to print a list of students
let printStudents title studentsList =
    printfn "\n%s" title
    studentsList
    |> List.iter (printfn "%A")

// Main 
[<EntryPoint>]
let main argv =

    // Print original list
    printStudents "Original Students:" students

    // Print filtered lists
    printStudents "Adult Students (Age > 18):" older
    printStudents "High grades (Grade > 80):" (goodGrade 80.0)

    // Print sorted lists
    printStudents "Sorted by Name:" sortedByName
    printStudents "Sorted by Grade Descending:" sortedByGradeDescending

    // Print transformed list with curved grades
    let curvedStudents = curvedGrades 5.0
    printStudents "Curved Grades (+5.0):" curvedStudents

    // Print formatted strings
    printfn "\nFormatted Student Info:"
    formatStudentInfo
    |> List.iter (printfn "%s")

    0 // Return exit code