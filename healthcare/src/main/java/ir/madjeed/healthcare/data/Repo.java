package ir.madjeed.healthcare.data;

import ir.madjeed.healthcare.data.impl.persistent.entity.UserPersistent;

public interface Repo {
    public RepoBase<UserPersistent, String> getRepoUsers();
}
