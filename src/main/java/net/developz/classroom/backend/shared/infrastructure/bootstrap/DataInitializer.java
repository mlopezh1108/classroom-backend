package net.developz.classroom.backend.shared.infrastructure.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Day;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Group;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Period;
import net.developz.classroom.backend.catalog.infrastructure.persistence.entity.Subject;
import net.developz.classroom.backend.catalog.infrastructure.persistence.repository.DayRepository;
import net.developz.classroom.backend.catalog.infrastructure.persistence.repository.GroupRepository;
import net.developz.classroom.backend.catalog.infrastructure.persistence.repository.PeriodRepository;
import net.developz.classroom.backend.catalog.infrastructure.persistence.repository.SubjectRepository;
import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Administrator;
import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Permission;
import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Person;
import net.developz.classroom.backend.iam.infrastructure.persistence.entity.PersonRole;
import net.developz.classroom.backend.iam.infrastructure.persistence.entity.Role;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.AdministratorRepository;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.PermissionRepository;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.PersonRepository;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.PersonRoleRepository;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.RoleRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@Profile("local")
@RequiredArgsConstructor
@SuppressWarnings("null")
public class DataInitializer implements CommandLineRunner {

    private final DayRepository dayRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PeriodRepository periodRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final PersonRepository personRepository;
    private final PersonRoleRepository personRoleRepository;
    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        log.info("Starting security and catalog data initialization...");

        seedDays();
        Set<Permission> allPermissions = seedPermissions();
        seedRoles(allPermissions);
        seedPeriods();
        seedSubjects();
        seedGroups();
        seedRootAdmin();

        log.info("Data initialization completed successfully.");
    }

    private void seedRootAdmin() {
        String email = "marcoslp86@gmail.com";
        if (personRepository.findByEmail(email).isEmpty()) {
            log.info("Seeding Root Administrator: {}", email);

            // 1. Create Person
            Person marcos = new Person();
            marcos.setFirstName("Marcos");
            marcos.setLastName("Lopez");
            marcos.setEmail(email);
            marcos.setPassword(passwordEncoder.encode("Admin123!"));
            marcos.setGovernmentId("ROOT-ADMIN-01");

            // 2. Find ADMIN Role
            Optional<Role> adminRoleOpt = roleRepository.findByRoleName("ADMIN");
            if (adminRoleOpt.isPresent()) {
                Role adminRole = adminRoleOpt.get();
                marcos.setDefaultRole(adminRole.getId());
                personRepository.save(marcos);

                // 3. Assign Role via PersonRole
                PersonRole personRole = new PersonRole();
                personRole.setPerson(marcos);
                personRole.setRole(adminRole);
                personRole.setActive(true);
                personRoleRepository.save(personRole);

                // 4. Create Administrator Profile
                Administrator adminProfile = new Administrator();
                adminProfile.setEmployeeNumber(1);
                adminProfile.setPerson(marcos);
                administratorRepository.save(adminProfile);

                log.info("Root Administrator created successfully with default password 'Admin123!'");
            } else {
                log.warn("Could not seed Root Administrator because 'ADMIN' role was not found.");
            }
        }
    }

    private void seedDays() {
        if (dayRepository.count() == 0) {
            log.info("Seeding Days of the week...");
            List<String> dayNames = Arrays.asList(
                    "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

            List<Day> days = dayNames.stream().map(name -> {
                Day day = new Day();
                day.setDayName(name);
                return day;
            }).collect(Collectors.toList());

            dayRepository.saveAll(days);
            log.info("{} days seeded.", days.size());
        }
    }

    private Set<Permission> seedPermissions() {
        List<String> permissionNames = Arrays.asList(
                // User Management
                "USER_VIEW", "USER_CREATE", "USER_UPDATE", "USER_DELETE",
                // Academic Management
                "COURSE_VIEW", "COURSE_CREATE", "COURSE_UPDATE", "COURSE_DELETE",
                "SUBJECT_VIEW", "SUBJECT_CREATE", "SUBJECT_UPDATE", "SUBJECT_DELETE",
                "RESOURCE_VIEW", "RESOURCE_CREATE", "RESOURCE_UPDATE", "RESOURCE_DELETE",
                "EXAM_VIEW", "EXAM_CREATE", "EXAM_UPDATE", "EXAM_DELETE",
                "EXAM_ATTEMPT_VIEW", "EXAM_ATTEMPT_CREATE", "EXAM_ATTEMPT_UPDATE",
                "EXAM_ATTEMPT_DELETE", "PERIOD_VIEW", "PERIOD_CREATE", "PERIOD_UPDATE",
                "PERIOD_DELETE", "GROUP_VIEW", "GROUP_CREATE", "GROUP_UPDATE",
                "GROUP_DELETE",
                // Operations
                "ENROLLMENT_VIEW", "ENROLLMENT_CREATE", "ENROLLMENT_UPDATE", "ENROLLMENT_DELETE",
                "ADVISORY_VIEW", "ADVISORY_CREATE", "ADVISORY_UPDATE", "ADVISORY_DELETE",
                "SCHEDULE_VIEW", "SCHEDULE_CREATE", "SCHEDULE_UPDATE", "SCHEDULE_DELETE",
                // Security
                "ROLE_VIEW", "ROLE_MANAGE", "PERMISSION_VIEW",
                // Reporting
                "REPORT_VIEW");

        Set<Permission> allPermissions = new HashSet<>();

        for (String name : permissionNames) {
            permissionRepository.findByPermissionName(name).ifPresentOrElse(
                    allPermissions::add,
                    () -> {
                        Permission p = new Permission();
                        p.setPermissionName(name);
                        p.setDescription("Permission to perform action: " + name);
                        permissionRepository.save(p);
                        allPermissions.add(p);
                        log.info("Permission created: {}", name);
                    });
        }

        return allPermissions;
    }

    private void seedRoles(Set<Permission> allPermissions) {
        if (roleRepository.count() == 0) {
            log.info("Seeding base Security Roles...");

            // ADMIN ROLE
            Role admin = new Role();
            admin.setRoleName("ADMIN");
            admin.setPermissions(allPermissions);
            roleRepository.save(admin);
            log.info("Role created: ADMIN with all available permissions.");

            // TEACHER ROLE
            Role teacher = new Role();
            teacher.setRoleName("TEACHER");
            Set<Permission> teacherPerms = allPermissions.stream()
                    .filter(p -> Arrays.asList("COURSE_VIEW", "ADVISORY_VIEW", "ENROLLMENT_VIEW",
                            "ADVISORY_CREATE", "ADVISORY_UPDATE", "ADVISORY_DELETE", "SCHEDULE_VIEW",
                            "SCHEDULE_CREATE", "SCHEDULE_UPDATE", "SCHEDULE_DELETE", "RESOURCE_VIEW",
                            "RESOURCE_CREATE", "RESOURCE_UPDATE", "RESOURCE_DELETE", "EXAM_VIEW",
                            "EXAM_CREATE", "EXAM_ATTEMPT_VIEW", "EXAM_ATTEMPT_CREATE",
                            "EXAM_ATTEMPT_UPDATE", "EXAM_ATTEMPT_DELETE")
                            .contains(p.getPermissionName()))
                    .collect(Collectors.toSet());
            teacher.setPermissions(teacherPerms);
            roleRepository.save(teacher);
            log.info("Role created: TEACHER.");

            // STUDENT ROLE
            Role student = new Role();
            student.setRoleName("STUDENT");
            Set<Permission> studentPerms = allPermissions.stream()
                    .filter(p -> Arrays.asList("COURSE_VIEW", "ENROLLMENT_VIEW", "ADVISORY_VIEW",
                            "ADVISORY_CREATE", "ADVISORY_UPDATE", "ADVISORY_DELETE", "SCHEDULE_VIEW",
                            "RESOURCE_VIEW", "EXAM_VIEW", "EXAM_ATTEMPT_VIEW", "EXAM_ATTEMPT_CREATE")
                            .contains(p.getPermissionName()))
                    .collect(Collectors.toSet());
            student.setPermissions(studentPerms);
            roleRepository.save(student);
            log.info("Role created: STUDENT.");
        }
    }

    private void seedPeriods() {
        if (periodRepository.count() == 0) {
            log.info("Seeding Periods...");
            Period p1 = new Period();
            p1.setPeriodCode("2026-1");
            p1.setStartDate(java.time.LocalDate.of(2026, 1, 12));
            p1.setEndDate(java.time.LocalDate.of(2026, 5, 22));

            Period p2 = new Period();
            p2.setPeriodCode("2026-2");
            p2.setStartDate(java.time.LocalDate.of(2026, 8, 10));
            p2.setEndDate(java.time.LocalDate.of(2026, 12, 18));

            periodRepository.saveAll(Arrays.asList(p1, p2));
            log.info("2 periods seeded.");
        }
    }

    private void seedSubjects() {
        if (subjectRepository.count() == 0) {
            log.info("Seeding Internationalized Subjects...");

            Subject math = createSubject("MATH-01", 1,
                    java.util.Map.of("en", "Mathematics I", "es", "Matemáticas I"));
            Subject phys = createSubject("PHYS-01", 1,
                    java.util.Map.of("en", "Physics I", "es", "Física I"));
            Subject prog = createSubject("PROG-01", 1,
                    java.util.Map.of("en", "Programming Fundamentals", "es", "Fundamentos de Programación"));
            Subject math2 = createSubject("MATH-02", 2,
                    java.util.Map.of("en", "Mathematics II", "es", "Matemáticas II"));

            subjectRepository.saveAll(Arrays.asList(math, phys, prog, math2));
            log.info("4 subjects seeded.");
        }
    }

    private Subject createSubject(String code, Integer level, java.util.Map<String, String> names) {
        Subject s = new Subject();
        s.setSubjectCode(code);
        s.setLevel(level);
        s.setSubjectNames(new java.util.HashMap<>(names));
        return s;
    }

    private void seedGroups() {
        if (groupRepository.count() == 0) {
            log.info("Seeding Groups...");
            List<String> codes = Arrays.asList("G-01", "G-02", "G-03");
            List<Group> groups = codes.stream().map(c -> {
                Group g = new Group();
                g.setGroupCode(c);
                return g;
            }).collect(Collectors.toList());

            groupRepository.saveAll(groups);
            log.info("3 groups seeded.");
        }
    }
}
