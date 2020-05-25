db.createUser(
    {
        user: "user1",
        pwd: "pw123",
        roles: [
            {
                role: "readWrite",
                db: "fullstack1"
            }
        ]
    }
);