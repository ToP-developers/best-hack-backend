package application.dao;

import application.entities.UserEntity;
import application.requests.Signup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    @NotNull
    @Override
    public UserEntity addAdvertiser(@NotNull Signup signup) {
        return null;
    }

    @Nullable
    @Override
    public UserEntity getAdvertiserById(@NotNull Long userId) {
        return null;
    }

    @Nullable
    @Override
    public UserEntity getAdvertiserByLogin(@NotNull String login) {
        return null;
    }

    @Nullable
    @Override
    public UserEntity getAdvertiserByEmail(@NotNull String email) {
        return null;
    }
}
