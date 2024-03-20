/*
  Warnings:

  - A unique constraint covering the columns `[name,belongsToId]` on the table `Songs` will be added. If there are existing duplicate values, this will fail.
  - Added the required column `belongsToId` to the `Songs` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "Songs" ADD COLUMN     "belongsToId" TEXT NOT NULL;

-- CreateIndex
CREATE UNIQUE INDEX "Songs_name_belongsToId_key" ON "Songs"("name", "belongsToId");

-- AddForeignKey
ALTER TABLE "Songs" ADD CONSTRAINT "Songs_belongsToId_fkey" FOREIGN KEY ("belongsToId") REFERENCES "User"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
