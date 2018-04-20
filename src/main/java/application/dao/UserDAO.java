package application.dao;

import application.entities.UserEntity;
import application.requests.Signup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface UserDAO {
    @NotNull
    UserEntity addAdvertiser(@NotNull Signup signup);

    @Nullable
    UserEntity getAdvertiserById(@NotNull Long userId);

    @Nullable
    UserEntity getAdvertiserByLogin(@NotNull String login);

    @Nullable
    UserEntity getAdvertiserByEmail(@NotNull String email);
}
