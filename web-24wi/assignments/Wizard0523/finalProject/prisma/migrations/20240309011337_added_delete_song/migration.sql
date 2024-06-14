/*
  Warnings:

  - A unique constraint covering the columns `[name,belongsToId]` on the table `Songs` will be added. If there are existing duplicate values, this will fail.

*/
-- CreateIndex
CREATE UNIQUE INDEX "Songs_name_belongsToId_key" ON "Songs"("name", "belongsToId");
