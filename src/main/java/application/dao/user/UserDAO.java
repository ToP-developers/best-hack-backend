package application.dao.user;

import application.entities.UserEntity;
import application.requests.Signup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface UserDAO {
    @NotNull
    UserEntity addUser(@NotNull Signup signup);

    @Nullable
    UserEntity getUserById(@NotNull Long userId);

    @Nullable
    UserEntity getUserByLogin(@NotNull String login);

    @Nullable
    UserEntity getUserByEmail(@NotNull String email);

    @Nullable
    UserEntity getUserByToken(@NotNull String token);
}
